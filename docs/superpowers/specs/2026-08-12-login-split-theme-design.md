# Login Page: Split-Screen with Role-Reactive Theme Panel

## Problem

Current login page (post tab-toggle fix) still uses the original per-card design:
each of the two tab panes is itself a 40/60 illustration+form card, full width,
~380px tall. Feels oversized and generic ("doesn't look professional"). User wants
a small, compact login form and a proper themed brand panel, in the pattern used by
mainstream fintech/SaaS products (Stripe, QuickBooks, Xero): a large left-hand
brand/illustration panel and a small right-hand form panel, with the brand panel's
content reacting to which login mode (Admin/Client) is selected.

## Goal

- Small, professional login card (~400px), not a full-width oversized panel.
- Fixed split-screen layout: themed panel left, compact form right.
- Left panel content (gradient, icon cluster, headline, trust bullets) changes to
  match the selected role when the user clicks the Admin/Client toggle.
- Self-contained: built from CSS gradients + Bootstrap Icons font already in the
  project — no external stock imagery, no new asset pipeline.

## Layout

- `.auth-shell` becomes a two-column flex/grid at `≥900px`: left `.theme-panel`
  (~58%, full viewport height), right `.form-side` (~42%, white background,
  vertically centered content).
- `.form-side` contains: page header (kept, smaller), alert messages, the
  existing Bootstrap nav-pills toggle, then a single small card (max-width
  `400px`) holding whichever form (`#admin-card`/`#client-card`) is active —
  same Bootstrap tab-pane mechanism from the previous fix, just re-skinned:
  the per-card illustration/gradient half is removed (that job moves to the
  left panel), each card becomes just the form fields at tighter padding.
- `.theme-panel` is one element with two content blocks inside it —
  `.theme-admin` and `.theme-client` — only one visible at a time via the same
  `show`/`active`-style pattern, driven by a `data-role` attribute on
  `.theme-panel` that the click handler updates in `auth.js`.

## Theme panel content (per role)

**Admin** (default):
- Gradient `#0B2A5B → #1D4ED8` (135deg), matches existing `--navy`/`--admin-accent`.
- Icon cluster: shield-lock (main), bar-chart, gear — reusing the existing
  orb/badge motif from the old illustration art, scaled up.
- Headline: "Admin Control Center" (28px/700, white).
- 3 trust bullets, each with a check-circle icon: "Full Audit Trail",
  "Role-Based Access", "Encrypted Sessions".

**Client**:
- Gradient `#0E7C5A → #34A98A` (135deg), matches existing `--client-accent`.
- Icon cluster: wallet (main), receipt/invoice, graph-up-arrow.
- Headline: "Your Finances, Simplified" (28px/700, white).
- 3 trust bullets: "Real-Time Reports", "Secure Document Vault",
  "Direct Accountant Support".

Both blocks live in the DOM at all times (simplifies server-side default
rendering via `activeTab`); CSS/JS toggles which is visible. Switching
crossfades via CSS `transition: opacity` (150ms) — no layout shift since both
blocks occupy the same grid cell.

## Behavior

- Clicking an Admin/Client pill already fires Bootstrap's `shown.bs.tab` event
  (native to the tab mechanism from the last fix). `auth.js` adds one listener
  on that event that sets `data-role="admin"|"client"` on `.theme-panel`; CSS
  attribute selectors show/hide `.theme-admin`/`.theme-client` accordingly.
- Initial `data-role` is set server-side from the same `activeTab` Thymeleaf
  variable already driving the form tabs (`th:attr="data-role=${activeTab}"`)
  — no flash of wrong theme on load.

## Responsive (<900px)

- `.theme-panel` display:none (unchanged behavior from before this redesign).
- `.form-side` goes full width, form card centered, same as current mobile
  layout.

## Out of scope

No changes to field validation, Spring Security config, `register.html`, or
`forgot-password.html`. No new icon assets beyond Bootstrap Icons already
loaded via webjar.

## Files touched

- `src/main/resources/templates/login.html` — restructure into `.theme-panel`
  + `.form-side`, add the two theme content blocks, shrink each form card.
- `src/main/resources/static/css/styles.css` — replace old per-card
  illustration CSS with `.theme-panel`/`.theme-admin`/`.theme-client` styles;
  new split-layout rules; smaller `.auth-card` form styling.
- `src/main/resources/static/js/auth.js` — add `shown.bs.tab` listener to sync
  `.theme-panel`'s `data-role`.
