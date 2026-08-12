# Login Tab Toggle Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Only one of Admin/Client login forms visible at a time, swapped via Bootstrap 5 native tabs.

**Architecture:** Convert existing pill-toggle + stacked-cards markup in `login.html` into a Bootstrap `nav-pills` + `tab-content`/`tab-pane` pair. Bootstrap's `tab.js` (via `bootstrap.bundle.min.js`) owns the show/hide; CSS keeps current visual design but keys off `.active` instead of the old `.is-inactive` scheme. `auth.js` drops its now-dead scroll/toggle code.

**Tech Stack:** Thymeleaf, Bootstrap 5.3.3 (webjar, CSS already included; JS bundle newly added to this page only), vanilla JS.

## Global Constraints
- Admin tab is the default active pane (matches `LoginController`'s `defaultValue = "admin"`).
- No visual/style changes beyond what the tab-class swap requires — colors, spacing, illustration panels, field styling stay as-is.
- No changes to `register.html`, `forgot-password.html`, or Spring Security config.
- Reuse the existing `activeTab` model attribute already produced by `LoginController` (no controller change).

---

### Task 1: Convert login page to Bootstrap tab pattern

**Files:**
- Modify: `src/main/resources/templates/login.html`
- Modify: `src/main/resources/static/css/styles.css`
- Modify: `src/main/resources/static/js/auth.js`

**Interfaces:**
- Consumes: `activeTab` model attribute (`"admin"` | `"client"`) already set by `LoginController.loginPage()` (`src/main/java/com/rraccountancy/app/controller/LoginController.java:33`).
- Produces: n/a (leaf template change, nothing downstream depends on new markup/class names).

- [ ] **Step 1: Replace the toggle + cards markup with Bootstrap nav-pills/tab-content**

In `src/main/resources/templates/login.html`, replace the block from `<div class="role-toggle" ...>` through the closing `</div>` of `.auth-cards` (lines 21–140) with:

```html
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
        <section class="auth-card admin tab-pane fade" th:classappend="${activeTab == 'admin'} ? 'show active' : ''"
                  id="admin-card" role="tabpanel" aria-labelledby="admin-tab">
            <div class="illustration">
                <div class="illustration-art">
                    <span class="art-orb orb1"></span>
                    <span class="art-orb orb2"></span>
                    <span class="art-badge b1"><i class="bi bi-gear-fill"></i></span>
                    <span class="art-badge b2"><i class="bi bi-cpu-fill"></i></span>
                    <span class="art-main"><i class="bi bi-shield-lock-fill"></i></span>
                </div>
                <div class="trust-strip"><i class="bi bi-shield-check"></i> Secure &bull; Private &bull; Protected</div>
            </div>
            <div class="form-panel">
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
        <section class="auth-card client tab-pane fade" th:classappend="${activeTab == 'client'} ? 'show active' : ''"
                  id="client-card" role="tabpanel" aria-labelledby="client-tab">
            <div class="illustration">
                <div class="illustration-art">
                    <span class="art-orb orb1"></span>
                    <span class="art-orb orb2"></span>
                    <span class="art-badge b1"><i class="bi bi-check-lg"></i></span>
                    <span class="art-badge b2"><i class="bi bi-envelope-fill"></i></span>
                    <span class="art-main"><i class="bi bi-person-workspace"></i></span>
                </div>
                <div class="trust-strip"><i class="bi bi-shield-check"></i> Your data is safe with us</div>
            </div>
            <div class="form-panel">
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
```

Also add the Bootstrap JS bundle right before the existing `auth.js` script tag at the bottom of the file:

```html
<script th:src="@{/webjars/bootstrap/5.3.3/js/bootstrap.bundle.min.js}"></script>
<script th:src="@{/js/auth.js}"></script>
```

- [ ] **Step 2: Retarget the toggle/card CSS from `.is-inactive` to Bootstrap's `.active`**

In `src/main/resources/static/css/styles.css`, replace the `/* Toggle */` block (current lines 77–113):

```css
/* Toggle */
.role-toggle {
    display: flex;
    width: 100%;
    max-width: 720px;
    height: 56px;
    border-radius: 999px;
    overflow: hidden;
    margin-bottom: 28px;
    box-shadow: 0 4px 14px rgba(11, 42, 91, 0.08);
}
.role-toggle .nav-link {
    flex: 1 1 50%;
    border: none;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;
    border-radius: 0;
    background: var(--inactive-bg);
    color: var(--inactive-text);
    transition: background-color .2s ease, color .2s ease;
}
.role-toggle .nav-link i { font-size: 18px; }

.role-toggle .toggle-admin.active {
    background: var(--navy);
    color: #fff;
}
.role-toggle .toggle-client.active {
    background: var(--client-accent);
    color: #fff;
}
```

Then find this line further down (currently around line 129):

```css
    scroll-margin-top: 24px;
```

Remove it — `scroll-margin-top` was only needed for the old `scrollIntoView` behavior, which is gone.

- [ ] **Step 3: Remove dead toggle/scroll JS, keep the eye-toggle**

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
})();
```

- [ ] **Step 4: Build and manually verify**

Run: `mvnw.cmd spring-boot:run` (or the project's existing run skill), open `http://localhost:8080/login`.

Expected:
- Only the Admin card is visible on load; Client card is not in the page flow (not just visually hidden — Bootstrap's `.fade`/`.show`/`.active` toggling means the inactive pane isn't rendered).
- Clicking "Client Login" pill swaps to the Client card; Admin card disappears. Clicking back swaps again.
- Password eye-toggle still works on both forms.
- Submitting a bad Client login (`/login?error&tab=client`) reopens on the Client tab, not Admin.
- Layout below 900px still collapses correctly (illustration panel hidden, full-width form).

- [ ] **Step 5: Commit**

```bash
git add src/main/resources/templates/login.html src/main/resources/static/css/styles.css src/main/resources/static/js/auth.js
git commit -m "fix(login): show one login form at a time via Bootstrap tabs

Admin/Client cards rendered stacked and both visible; toggle only
re-colored the pills and scrolled. Switch to Bootstrap 5 nav-pills +
tab-content so only the selected form renders, with native ARIA/keyboard
support. Drop the now-dead scroll/toggle JS.

Co-Authored-By: Claude Sonnet 5 <noreply@anthropic.com>
Claude-Session: https://claude.ai/code/session_01UAmCbJjQrNHTLD8UMHUCYH"
```
