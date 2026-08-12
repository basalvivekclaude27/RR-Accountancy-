# Login Page: True Tab Toggle (Admin ⇄ Client)

## Problem

`login.html` already has the dual-card admin/client design from `Specs/00_login.md`
(gradient illustration panel + form panel, both cards styled). But `auth.js`'s
`activate()` only re-colors the pill buttons and scrolls — it never hides a card.
Result: both Admin and Client login forms render stacked and visible at once, which
is the bug the user is reporting ("both login displaying together").

## Goal

Only one login form visible at a time. Clicking the toggle swaps which one shows.
Reuse Bootstrap's native tab machinery instead of hand-rolled show/hide, per
"bootstrap best practices" ask — full ARIA/keyboard support for free, less custom JS
to maintain.

## Approach

Convert the existing pill toggle + two `.auth-card` sections into a standard
Bootstrap 5 tabs pattern:

- `.role-toggle` becomes a `<ul class="nav nav-pills role-toggle">` with two
  `button.nav-link[data-bs-toggle="pill"][data-bs-target="#admin-card"|"#client-card"]`,
  `role="tab"`, `aria-selected`. Existing pill visuals (navy/green halves, full
  rounded pill, 56px height) are kept — CSS selectors retarget from the old
  `.is-inactive` (inactive-marked) scheme to Bootstrap's `.active` class
  (active-marked), which is the inverse but visually identical end state.
- The two `.auth-card` sections move inside `<div class="tab-content">`, each
  gaining `tab-pane fade` (+ `show active` on whichever is the initial tab).
  Bootstrap's `tab.js` toggles `.show`/`.active` on click — no custom JS needed
  for the switch itself.
- Server already resolves `activeTab` (`admin` default, `client` via
  `?tab=client` / hidden `tab` form field round-tripped through
  `LoginController` on a failed login) — this drives which pane starts
  `show active`, so a failed Client login re-opens the Client tab, not the
  default Admin one. No controller change needed.
- `auth.js` loses its custom `activate()`/scroll logic (dead code once Bootstrap
  drives the pane swap); the password show/hide eye toggle logic stays as-is.
- Add `bootstrap.bundle.min.js` (webjar, already a CSS dependency in
  `fragments/head.html`) to `login.html` only — it's the one page using
  JS-driven Bootstrap components; no other template needs it today.
- Default initial tab: **Admin** (matches current left-pill-first convention
  and controller's existing `defaultValue = "admin"`).

## Out of scope

No visual redesign beyond what's needed for the tab mechanics (colors, spacing,
illustration panels, field styling all stay as currently spec'd). No changes to
`register.html`, `forgot-password.html`, or the security config.

## Files touched

- `src/main/resources/templates/login.html` — nav-pills + tab-content markup,
  add bootstrap bundle JS script tag.
- `src/main/resources/static/css/styles.css` — retarget `.role-toggle`/`.auth-card`
  active-state selectors to Bootstrap's tab classes.
- `src/main/resources/static/js/auth.js` — remove dead toggle/scroll code, keep
  eye-toggle code.
