# Login Split-Theme Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Small, professional split-screen login: compact form card on the right, a
role-reactive themed brand panel (gradient + icon cluster + headline + trust
bullets) on the left that swaps Admin ⇄ Client content when the toggle is clicked.

**Architecture:** New `.login-shell` two-column layout used only by `login.html`.
Existing `.auth-shell`/`.auth-card`/`.form-panel` base classes are left **byte-for-byte
untouched** because `register.html`, `forgot-password.html`, `error.html`, and
`403.html` all depend on them (confirmed via grep — none of those four use
`.illustration`/`.art-*`/`.trust-strip`, so those old classes are safe to delete
outright). Login's per-card illustration panel is deleted; its job moves to the
new shared `.theme-panel`, which shows/hides two pre-rendered content blocks
(`.theme-admin`/`.theme-client`) via a `data-role` attribute kept in sync with the
Bootstrap tab selection.

**Tech Stack:** Thymeleaf, Bootstrap 5.3.3 (nav-pills/tab-content from the prior
fix), Bootstrap Icons, vanilla JS.

## Global Constraints
- `.auth-shell`, `.auth-card` (base + `.admin`/`.client` color rules), `.auth-card
  .form-panel`, `.field-*`, `.row-between`, `.remember-me`, `.forgot-link`,
  `.btn-submit`, `.form-footer`, `.register-wrap` CSS rules must not change —
  shared by register/forgot-password/error/403.
- `.illustration`, `.illustration-art`, `.art-orb`, `.art-badge`, `.art-main`,
  `.trust-strip` CSS rules are deleted outright (confirmed unused outside
  login.html).
- No external images/stock photos — gradients + Bootstrap Icons font only.
- Left theme panel hidden below 900px (unchanged behavior from current
  breakpoint), form goes full width.
- Admin is the default active role (matches existing `activeTab` default).

---

### Task 1: Split-screen layout with role-reactive theme panel

**Files:**
- Modify: `src/main/resources/templates/login.html`
- Modify: `src/main/resources/static/css/styles.css`
- Modify: `src/main/resources/static/js/auth.js`

**Interfaces:**
- Consumes: `activeTab` model attribute (`"admin"`|`"client"`) from
  `LoginController.loginPage()` (unchanged, already exists).
- Produces: n/a (leaf template).

- [ ] **Step 1: Replace `login.html` body**

Replace everything between `<body>` and `</body>` with:

```html
<body>
<div class="login-shell">

    <div class="theme-panel" th:attr="data-role=${activeTab}">
        <div class="theme-content theme-admin">
            <div class="theme-icon-cluster">
                <span class="tic-orb orb1"></span>
                <span class="tic-orb orb2"></span>
                <span class="tic-badge b1"><i class="bi bi-bar-chart-fill"></i></span>
                <span class="tic-badge b2"><i class="bi bi-gear-fill"></i></span>
                <span class="tic-main"><i class="bi bi-shield-lock-fill"></i></span>
            </div>
            <h2 class="theme-headline">Admin Control Center</h2>
            <p class="theme-sub">Manage clients, jobs, and firm operations from one secure dashboard.</p>
            <ul class="theme-trust-list">
                <li><i class="bi bi-check-circle-fill"></i> Full Audit Trail</li>
                <li><i class="bi bi-check-circle-fill"></i> Role-Based Access</li>
                <li><i class="bi bi-check-circle-fill"></i> Encrypted Sessions</li>
            </ul>
        </div>
        <div class="theme-content theme-client">
            <div class="theme-icon-cluster">
                <span class="tic-orb orb1"></span>
                <span class="tic-orb orb2"></span>
                <span class="tic-badge b1"><i class="bi bi-receipt"></i></span>
                <span class="tic-badge b2"><i class="bi bi-graph-up-arrow"></i></span>
                <span class="tic-main"><i class="bi bi-wallet2"></i></span>
            </div>
            <h2 class="theme-headline">Your Finances, Simplified</h2>
            <p class="theme-sub">Track accounts, view reports, and stay on top of every deadline.</p>
            <ul class="theme-trust-list">
                <li><i class="bi bi-check-circle-fill"></i> Real-Time Reports</li>
                <li><i class="bi bi-check-circle-fill"></i> Secure Document Vault</li>
                <li><i class="bi bi-check-circle-fill"></i> Direct Accountant Support</li>
            </ul>
        </div>
    </div>

    <div class="form-side">
        <div class="form-side-inner">

            <div class="auth-header">
                <h1>Welcome to R &amp; R Accountancy Services</h1>
                <p>Please choose how you want to continue</p>
            </div>

            <div class="auth-alert error" th:if="${errorMessage}">
                <i class="bi bi-exclamation-circle-fill"></i>
                <span th:text="${errorMessage}">Error</span>
            </div>
            <div class="auth-alert info" th:if="${infoMessage}">
                <i class="bi bi-check-circle-fill"></i>
                <span th:text="${infoMessage}">Info</span>
            </div>

            <ul class="nav nav-pills role-toggle" role="tablist" aria-label="Choose login type">
                <li class="nav-item flex-fill" role="presentation">
                    <button type="button" class="nav-link toggle-admin w-100"
                            id="admin-tab" data-bs-toggle="pill" data-bs-target="#admin-card"
                            role="tab" aria-controls="admin-card"
                            th:classappend="${activeTab == 'admin'} ? 'active' : ''"
                            th:attr="aria-selected=${activeTab == 'admin'}">
                        <i class="bi bi-person-fill"></i> Admin Login
                    </button>
                </li>
                <li class="nav-item flex-fill" role="presentation">
                    <button type="button" class="nav-link toggle-client w-100"
                            id="client-tab" data-bs-toggle="pill" data-bs-target="#client-card"
                            role="tab" aria-controls="client-card"
                            th:classappend="${activeTab == 'client'} ? 'active' : ''"
                            th:attr="aria-selected=${activeTab == 'client'}">
                        <i class="bi bi-person-fill"></i> Client Login
                    </button>
                </li>
            </ul>

            <div class="tab-content auth-cards">

                <!-- Admin card -->
                <section class="auth-card compact admin tab-pane fade" th:classappend="${activeTab == 'admin'} ? 'show active' : ''"
                          id="admin-card" role="tabpanel" aria-labelledby="admin-tab">
                    <div class="form-panel" style="flex:1 1 100%; margin:0;">
                        <h2>Admin Login</h2>
                        <p class="form-sub">Secure access for administrators only</p>

                        <form th:action="@{/login}" method="post" novalidate>
                            <input type="hidden" name="tab" value="admin"/>

                            <div class="field-group">
                                <label for="admin-identifier">Email Address</label>
                                <div class="field-wrap">
                                    <i class="bi bi-envelope-fill field-icon"></i>
                                    <input type="text" id="admin-identifier" name="identifier"
                                           placeholder="Enter admin email" autocomplete="username" required/>
                                </div>
                            </div>

                            <div class="field-group">
                                <label for="admin-password">Password</label>
                                <div class="field-wrap">
                                    <i class="bi bi-lock-fill field-icon"></i>
                                    <input type="password" id="admin-password" name="password"
                                           placeholder="Enter password" autocomplete="current-password" required/>
                                    <button type="button" class="toggle-eye bi bi-eye" data-target="admin-password" aria-label="Show password"></button>
                                </div>
                            </div>

                            <div class="row-between">
                                <div class="remember-me">
                                    <input type="checkbox" id="admin-remember" name="remember-me" value="true" checked/>
                                    <label for="admin-remember">Remember Me</label>
                                </div>
                                <a th:href="@{/forgot-password}" class="forgot-link">Forgot Password?</a>
                            </div>

                            <button type="submit" class="btn-submit">
                                Login as Admin <i class="bi bi-arrow-right"></i>
                            </button>
                        </form>
                    </div>
                </section>

                <!-- Client card -->
                <section class="auth-card compact client tab-pane fade" th:classappend="${activeTab == 'client'} ? 'show active' : ''"
                          id="client-card" role="tabpanel" aria-labelledby="client-tab">
                    <div class="form-panel" style="flex:1 1 100%; margin:0;">
                        <h2>Client Login</h2>
                        <p class="form-sub">Access your account and manage your records</p>

                        <form th:action="@{/login}" method="post" novalidate>
                            <input type="hidden" name="tab" value="client"/>

                            <div class="field-group">
                                <label for="client-identifier">Email Address</label>
                                <div class="field-wrap">
                                    <i class="bi bi-envelope-fill field-icon"></i>
                                    <input type="text" id="client-identifier" name="identifier"
                                           placeholder="Enter email address" autocomplete="username" required/>
                                </div>
                            </div>

                            <div class="field-group">
                                <label for="client-password">Password</label>
                                <div class="field-wrap">
                                    <i class="bi bi-lock-fill field-icon"></i>
                                    <input type="password" id="client-password" name="password"
                                           placeholder="Enter password" autocomplete="current-password" required/>
                                    <button type="button" class="toggle-eye bi bi-eye" data-target="client-password" aria-label="Show password"></button>
                                </div>
                            </div>

                            <div class="row-between">
                                <div class="remember-me">
                                    <input type="checkbox" id="client-remember" name="remember-me" value="true" checked/>
                                    <label for="client-remember">Remember Me</label>
                                </div>
                                <a th:href="@{/forgot-password}" class="forgot-link">Forgot Password?</a>
                            </div>

                            <button type="submit" class="btn-submit">
                                Login as Client <i class="bi bi-arrow-right"></i>
                            </button>
                        </form>

                        <p class="form-footer">New Client? <a th:href="@{/register}">Register Here</a></p>
                    </div>
                </section>

            </div>
        </div>
    </div>
</div>

<script th:src="@{/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js}"></script>
<script th:src="@{/js/auth.js}"></script>
</body>
```

- [ ] **Step 2: Delete the old illustration/art/trust-strip CSS**

In `src/main/resources/static/css/styles.css`, delete these rule blocks entirely
(confirmed unused outside login.html):
`.auth-card .illustration`, `.auth-card.admin .illustration`,
`.auth-card.client .illustration`, `.illustration-art` and all its `.art-orb`/
`.art-badge`/`.art-main` children, `.trust-strip`. This is the block currently
between `.auth-cards { ... }` and `.auth-card .form-panel { ... }`.

- [ ] **Step 3: Tighten the `.role-toggle` pill sizing**

Change `.role-toggle` height from `56px` to `48px` and `margin-bottom` from
`28px` to `20px`. Change `.role-toggle .nav-link` `font-size` from `16px` to
`15px`.

- [ ] **Step 4: Simplify `.auth-cards`**

Replace:
```css
.auth-cards {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 28px;
}
```
with:
```css
.auth-cards { width: 100%; }
```
(Only one tab-pane is ever visible now, so no gap/column layout is needed.)

- [ ] **Step 5: Add `.login-shell` and `.compact` card override CSS**

Add this block right after the `.auth-shell { ... }` rule (do not modify
`.auth-shell` itself):

```css
/* Login page split-screen shell (scoped, does not affect .auth-shell users:
   register/forgot-password/error/403) */
.login-shell {
    min-height: 100vh;
    display: flex;
    align-items: stretch;
}

.form-side {
    flex: 1 1 42%;
    display: flex;
    align-items: center;
    justify-content: center;
    background: #fff;
    padding: 40px 24px;
}
.form-side-inner { width: 100%; max-width: 400px; }
.form-side .auth-header { text-align: left; margin-bottom: 24px; }
.form-side .auth-header h1 { font-size: 22px; }
.form-side .auth-header p { font-size: 13px; }
.form-side .auth-alert,
.form-side .role-toggle { max-width: none; }

/* Compact card override — scoped to .compact so register/forgot-password's
   .auth-card usage (which lacks this class) is unaffected. */
.auth-card.compact { min-height: auto; }
.auth-card.compact .form-panel { padding: 28px 30px; }
.auth-card.compact .form-panel h2 { font-size: 22px; }
.auth-card.compact .field-wrap input { height: 44px; }
.auth-card.compact .btn-submit { height: 46px; }
```

- [ ] **Step 6: Add the theme panel CSS**

Add this block right after the `.compact` rules from Step 5:

```css
/* Theme panel (left side) — role-reactive brand/illustration panel */
.theme-panel {
    flex: 0 0 58%;
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 48px;
    overflow: hidden;
    color: #fff;
    transition: background .25s ease;
}
.theme-panel[data-role="admin"] {
    background: linear-gradient(135deg, var(--navy) 0%, var(--admin-accent-2) 100%);
}
.theme-panel[data-role="client"] {
    background: linear-gradient(135deg, var(--client-accent) 0%, var(--client-accent-2) 100%);
}

.theme-content {
    display: none;
    max-width: 420px;
    opacity: 0;
    transition: opacity .2s ease;
}
.theme-panel[data-role="admin"] .theme-admin,
.theme-panel[data-role="client"] .theme-client {
    display: block;
    opacity: 1;
}

.theme-icon-cluster {
    position: relative;
    width: 180px;
    height: 180px;
    margin-bottom: 32px;
}
.theme-icon-cluster .tic-orb {
    position: absolute;
    border-radius: 50%;
    background: rgba(255, 255, 255, 0.14);
}
.theme-icon-cluster .tic-orb.orb1 { width: 120px; height: 120px; top: -10px; left: 0; }
.theme-icon-cluster .tic-orb.orb2 { width: 64px; height: 64px; bottom: 0; right: 10px; background: rgba(255,255,255,.2); }
.theme-icon-cluster .tic-badge {
    position: absolute;
    width: 52px;
    height: 52px;
    border-radius: 14px;
    background: rgba(255, 255, 255, 0.2);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 22px;
}
.theme-icon-cluster .tic-badge.b1 { top: 4px; right: 6px; }
.theme-icon-cluster .tic-badge.b2 { bottom: 22px; left: 0; }
.theme-icon-cluster .tic-main {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 96px;
    height: 96px;
    border-radius: 50%;
    background: rgba(255, 255, 255, 0.16);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 42px;
    box-shadow: inset 0 0 0 1px rgba(255,255,255,.3);
}

.theme-headline { font-size: 28px; font-weight: 700; margin: 0 0 10px; color: #fff; }
.theme-sub { font-size: 15px; line-height: 1.5; margin: 0 0 24px; color: rgba(255,255,255,.85); }
.theme-trust-list { list-style: none; margin: 0; padding: 0; }
.theme-trust-list li {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 6px 0;
    font-size: 14.5px;
    color: rgba(255,255,255,.95);
}
.theme-trust-list i { color: #fff; font-size: 16px; }
```

- [ ] **Step 7: Replace the old responsive block**

Replace:
```css
/* Responsive */
@media (max-width: 900px) {
    .auth-card { flex-direction: column; }
    .auth-card .illustration { display: none; }
    .auth-card .form-panel { flex: 1 1 100%; margin: 0; border-radius: 0; padding: 20px; }
    .role-toggle { width: 100%; }
}
```
with:
```css
/* Responsive */
@media (max-width: 900px) {
    .login-shell { flex-direction: column; min-height: auto; }
    .theme-panel { display: none; }
    .form-side { padding: 32px 20px; }
    .auth-card.compact .form-panel { padding: 20px; }
}
```

- [ ] **Step 8: Sync theme panel to the active tab in `auth.js`**

Replace the full contents of `src/main/resources/static/js/auth.js` with:

```javascript
(function () {
    'use strict';

    // Password show/hide eye toggle
    document.querySelectorAll('.toggle-eye').forEach(function (btn) {
        btn.addEventListener('click', function () {
            var input = document.getElementById(btn.getAttribute('data-target'));
            if (!input) return;
            var showing = input.type === 'text';
            input.type = showing ? 'password' : 'text';
            btn.classList.toggle('bi-eye', showing);
            btn.classList.toggle('bi-eye-slash', !showing);
        });
    });

    // Login page: sync the left theme panel to whichever role tab is active.
    var themePanel = document.querySelector('.theme-panel');
    if (themePanel) {
        document.querySelectorAll('[data-bs-toggle="pill"]').forEach(function (btn) {
            btn.addEventListener('shown.bs.tab', function (e) {
                var role = e.target.id === 'admin-tab' ? 'admin' : 'client';
                themePanel.setAttribute('data-role', role);
            });
        });
    }
})();
```

- [ ] **Step 9: Build and manually verify**

Run: `mvnw`/project run skill, open `http://localhost:7000/login` (DB must be
up — see `start.bat`).

Expected:
- Page loads as two full-height columns: navy/blue gradient theme panel with
  icon cluster + "Admin Control Center" on the left (~58% width), small white
  card (~400px) with the Admin login form on the right.
- Clicking "Client Login" pill: right side swaps to the Client form (existing
  tab behavior), left side crossfades to teal/green gradient with "Your
  Finances, Simplified" and the client icon cluster/trust bullets.
- Card is visibly smaller than before — no more 380px-tall oversized panel.
- Below 900px: theme panel disappears, form is centered full-width.
- Visit `/register`, `/forgot-password`, and trigger `/403` — all three must
  render exactly as before (centered single card, no split layout, no theme
  panel) — confirms the shared `.auth-shell`/`.auth-card`/`.form-panel` rules
  were not altered.
- Failed login on the Client tab (`/login?error&tab=client`) reopens on the
  Client tab with the Client theme already showing (server-rendered
  `data-role`, no flash of the wrong theme).

- [ ] **Step 10: Commit**

```bash
git add src/main/resources/templates/login.html src/main/resources/static/css/styles.css src/main/resources/static/js/auth.js
git commit -m "redesign(login): compact split-screen with role-reactive theme panel

Replace the oversized per-card illustration layout with a small ~400px
form card on the right and a full-height themed brand panel on the left
that crossfades between Admin/Client content (gradient, icon cluster,
headline, trust bullets) as the toggle is used. Scoped new CSS under
.login-shell/.compact so register/forgot-password/error/403 - which
share .auth-shell/.auth-card/.form-panel - render unchanged.

Co-Authored-By: Claude Sonnet 5 <noreply@anthropic.com>
Claude-Session: https://claude.ai/code/session_01UAmCbJjQrNHTLD8UMHUCYH"
```
