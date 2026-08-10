# Personal Income Tax Screen — Look & Feel

Same shell as `BusinessPlan.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Personal Income Tax`.

## Topbar
- Title `Personal Income Tax` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Personal Income Tax` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Expert guidance. Accurate filing. Maximum savings. Complete peace of mind.` 14px `#6B7A94`.
- Button right: `+ New Tax Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94` with inline `(This Year)` 12px `#8494AC`; value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 124 | +15 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| Returns Filed (This Year) | 98 | +11 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Tax Refunds (This Year) | £285,450 | +18.6% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| Avg. Refund Amount | £2,912 | +9.4% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` |
| In Progress | 26 | `View details` link `#2E5BFF` | `#2E5BFF` / `#EAF2FE` (clock icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Tax Year (`2024 - 2025`, pre-selected), Client Type (`All Types`), Return Status (`All Status`), Assigned To (`All Staff`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Tax Returns Overview table
- Header: `Tax Returns Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or UTR...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client Name · UTR · Tax Year · Status · Refund / (Payable) · Last Updated · Actions`.
- Rows height `46px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + `UTR: 1234567890` 11.5px `#7A8AA3`.
- UTR / Tax Year / Last Updated 12.5px `#4A5B75`.
- Refund column: positive `+£3,250` 12.5px/600 `#16A34A`; empty state `—` `#9AA7BC`; payable values would be `(£x)` `#E2564F`.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`; Refund Issued `#15803D` on `#E6F6EC`.
- Actions: eye + download (or pencil for in-progress rows) `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 124 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Tax Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E` (68%), `#2E5BFF` (23%), `#F59E0B` (9%); center `£285,450` 17px/700 `#0F2544` + `Total Refunds` 11.5px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — Total Refunds £285,450 (68%), Tax Payable £98,350 (23%), In Progress £34,600 (9%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `25px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New Tax Return, Upload Documents, Tax Return Checklist, View Key Tax Dates, Tax Calculation Tool, View All Clients.

**Important Tax Dates** — title + `View all dates` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + month `OCT`/`JAN`/`JUL` 10px uppercase) accent red `#E2564F` / amber `#D98324` / green `#16A34A` / violet `#7C5CE0`; title 13.5px/600 `#1B3358`, sub tax-year 12.5px `#7A8AA3`; right `In 83 days` 12.5px/600 accent — Paper Return Deadline (2024 - 2025), Self Assessment Deadline (2024 - 2025), Payment on Account (1st) (2025 - 2026), Payment on Account (2nd) (2025 - 2026). Footer `+ View all key dates →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Refund Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line `#16A34A`, 2.5px stroke, circular white-fill markers with green ring, gradient fill `rgba(34,197,94,.12) → transparent`.
- Y axis `£0–£400K` steps `£100K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Legend below centred: swatch + `Refund Amount` 12.5px `#4A5B75`.
- Divider then footer row: left `Total Refunds` 12.5px `#6B7A94` over `£285,450` 18px/700 `#0F2544`; right `+18.6% vs last year` 12.5px/600 `#16A34A`.

### Refund vs Payable (This Year)
- Donut left ~`120px`, thickness `28px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#F59E0B`.
- Legend right: Refunds £285,450 (68%), Tax Payable £98,350 (23%), In Progress £34,600 (9%).
- Divider then centred block: `Net Position (Refunds - Payable)` 12.5px `#6B7A94` + `£187,100` 20px/700 `#16A34A`.

### Top Refunds (This Year)
- Rows height `31px`, divider `1px #F2F5FA`: client name 13px `#33455F` left, amount 13px/600 `#0F2544` right — Neha Thakkar £5,640; John Miller £5,230; Amit Kumar £4,780; Lisa Roberts £4,250; David Wilson £3,980.
- Footer centred link `View all top refunds →` 13px `#2E5BFF`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; bottom row 2 + 1; table hides `Tax Year`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, status, refund); bottom cards single column.
