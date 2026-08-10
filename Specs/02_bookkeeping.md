# Bookkeeping Screen — Look & Feel

Reuses the shell from `dashboard.md` (sidebar `272px` navy `#0B2A5B`, gold active item, bottom trust card, navy footer strip). Only the topbar and content differ.

## Topbar
- White, height `72px`, bottom border `1px #EDF1F7`.
- Left: hamburger button — `40px` circle, border `1px #E2E8F2`, navy icon.
- Page title `Bookkeeping` 20px/700 `#0B2A5B`; breadcrumb beneath 13px — `Dashboard` link `#2E5BFF` › chevron `#9AA7BC` › `Bookkeeping` `#6B7A94`.
- Right: bell + red count badge, divider, avatar `42px`, name/role, chevron.
- Active sidebar item = `Bookkeeping`.

## Page header card
- White card, radius `14px`, padding `24px 26px`, contains header row + KPI grid.
- Title `Bookkeeping` 24px/700 `#0F2544`; sub `Accurate records, better decisions. We keep your books in perfect order.` 14px `#6B7A94`.
- Primary button top-right: `+ New Bookkeeping Job` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 20px`.

### KPI strip (5 cards)
- Grid 5 × equal, gap `16px`; card white, radius `12px`, border `1px #EDF1F7`, padding `16px`.
- Icon tile `44px` radius `10px` tinted; label 13.5px `#6B7A94`; value 26px/700 `#0F2544`; footnote 12.5px accent.
- Accents: Total Clients `#3B82F6`/`#EAF2FE`; Active Jobs `#16A34A`/`#E8F6EC`; Completed Jobs `#F59E0B`/`#FEF2E3`; Pending Transactions `#8B5CF6`/`#F1ECFE` (footnote is a link `View to reconcile` `#2E5BFF`); This Month Revenue `#2E5BFF`/`#EAF2FE`.

## Main grid
Three columns: filters `~200px` · table (fluid) · right rail `~300px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`.
- Header: funnel icon `#2E5BFF` + `Filters` 16px/600.
- Field label 13px/600 `#33455F`, margin-bottom `6px`.
- Select/input: height `42px`, radius `8px`, border `1px #E2E8F2`, bg white, 13.5px, chevron/calendar icon `#9AA7BC` right.
- Fields: Client (`All Clients`), Job Status (`All Status`), Period (`Select Period`, date icon), Category (`All Categories`), Assigned To (`All Staff`).
- `Apply Filters`: full width, height `44px`, bg `#0B2A5B`, white 14px/600, radius `8px`.
- `Reset`: full width, height `44px`, white bg, border `1px #E2E8F2`, text `#33455F`.

### Jobs table card
- White, radius `12px`. Header row: title `Bookkeeping Jobs` 17px/600 + search box right — width `260px`, height `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or job...`, magnifier `#9AA7BC` right.
- Table head: bg `#FAFBFD`, 12.5px/600 uppercase-ish `#5A6B85`, columns `Client / Business · Period · Category · Status · Last Updated · Actions`, borders top/bottom `1px #EDF1F7`.
- Rows height `55px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `34px` circle avatar with 3-letter initials 11.5px/600 on tinted pastel bg (rotating: pink `#FBE9EC`, amber `#FDF1DC`, mint `#E5F5EC`, lilac `#F0EAFB`, blue `#E8F0FE`); name 14px/600 `#1B3358` + invoice ref `INV-1005` 12.5px `#7A8AA3` beneath.
- Period / Category / Last Updated: 13.5px `#4A5B75`.
- Status pill: radius `999px`, 12px/600, padding `4px 12px` — In Progress `#1D4ED8` on `#E8F0FE`; Pending Review `#B45309` on `#FEF3DE`; Completed `#15803D` on `#E6F6EC`; Pending Info `#6D28D9` on `#F1EAFD`.
- Actions: eye + pencil icon buttons `28px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical-dots `#9AA7BC`.
- Footer: left `Showing 1 to 6 of 36 entries` 13px `#6B7A94`; right pagination — square buttons `34px`, radius `6px`, border `1px #E2E8F2`; active page bg `#0B2A5B` white; controls `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Bank Feeds Overview** — card radius `12px`, header 16px/600 + `View All` link `#2E5BFF` 13px. Body: bank icon tile `56px` radius `12px` bg `#EAF2FE` navy glyph, beside a 3-row list (label 13.5px `#6B7A94` left, value 14px/600 `#0F2544` right): Connected Accounts `18`, To Reconcile `248`, Last Updated `08 Aug 2025`.

**Quick Actions** — title 16px/600; rows height `31px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, right chevron `#B4BECD`, hover bg `#F7FAFF`. Items: Upload Bank Statement, Upload Sales Invoices, Upload Purchase Invoices, Reconcile Transactions, Manage Categories, Chart of Accounts.

**Reminders** — title + `View All`. Rows: icon tile `34px` radius `8px` tinted (blue / amber / violet); title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right date 12.5px/600 in accent (`#E2564F`, `#D98324`, `#7C5CE0`). Footer link `+ See all reminders` 13px `#2E5BFF`.

## Bottom row (2 cards)
### Monthly Summary (This Month)
- Title 17px/600 + `(This Month)` 14px `#6B7A94`.
- 4 equal segments, vertical dividers `1px #EDF1F7`.
- Each: icon tile `34px` radius `8px` tinted + label 12.5px `#6B7A94` (2 lines allowed); value 24px/700 `#0F2544`; delta 12px `#16A34A`.
- Values: Transactions Added `1,245` (+15%), Invoices Processed `342` (+10%), Bank Reconciled `18` (+20%), Expenses Recorded `£8,450` (+12%).

### Top Expense Categories
- Donut left (~`130px`, thickness `26px`, segmented arcs with small gaps): `#8B5CF6`, `#22C55E`, `#F59E0B`, `#3B82F6`.
- Legend right: dot `9px` + label 13.5px `#33455F`, right amount + percent 13.5px `#4A5B75` — Staff Costs £3,250 (38%), Rent & Rates £2,100 (25%), Office Expenses £1,450 (17%), Other Expenses £1,650 (20%).
- Footer: `Total:` 13px `#6B7A94` + `£8,450` 14px/700 `#0F2544`, right-aligned.

## Palette
Inherits `dashboard.md` tokens: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`, amber `#F59E0B`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps to 3 + 2.
- `<1280px`: right rail moves below table; filters + table side by side.
- `<1024px`: sidebar → `72px` icon rail; filters collapse into a toggle drawer.
- `<768px`: table becomes stacked cards (client + status + date); bottom row single column.
