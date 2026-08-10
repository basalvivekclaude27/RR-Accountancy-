# Service Charge Accounts Screen — Look & Feel

Same shell as `BudgetForecasting.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Service Charge Accounts`.

## Topbar
- Title `Service Charge Accounts` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Service Charge Accounts` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Transparent accounting. Timely recovery. Complete compliance.` 14px `#6B7A94`.
- Button right: `+ New Service Charge Account` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94` with inline `(This Month)` 12px `#8494AC`; value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Properties | 42 | +4 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| Accounts Managed | 42 | +4 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Total Service Charge (This Month) | £82,650 | +12.8% vs last month `#16A34A` | `#F59E0B` / `#FEF2E3` |
| Amount Recovered (This Month) | £67,330 | 81.5% Collection Rate `#8B5CF6` | `#8B5CF6` / `#F1ECFE` |
| Outstanding Amount | £15,330 | -8.3% vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Property (`All Properties`), Financial Year (`2024 - 2025`, pre-selected), Status (`All Status`), Collection Status (`All`), Period (`This Month`, calendar icon).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Service Charge Accounts table
- Header: `Service Charge Accounts` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search property or account...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Property / Development · Unit(s) · Financial Year · Period · Budgeted Amount · Collected Amount · Collection % · Status · Actions`.
- Rows height `52px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Property cell: `34px` rounded-square thumbnail (radius `8px`) with building/photo image, then name 13.5px/600 `#1B3358` + ref `MC-1001` 12px `#7A8AA3`.
- Unit(s) / Financial Year / Period / amounts 13px `#4A5B75`, amounts 600 weight.
- Collection %: percent 12.5px/600 `#33455F` above a mini progress bar — track `#EDF1F7`, height `5px`, radius `3px`, width `~58px`; fill green `#22C55E` (≥80%), amber `#F59E0B` (70–79%), red-amber `#F97316` (<75% behind).
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — On Track `#15803D` on `#E6F6EC`; Attention `#B45309` on `#FEF3DE`; Behind `#DC2626` on `#FDECEC`.
- Actions: eye + download `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 42 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Collection Summary (This Month)** — header 16px/600 + `(This Month)` 13px `#6B7A94`.
- Donut left ~`115px`, thickness `26px`, two arcs: Collected `#22C55E` (81.5%), Outstanding `#F59E0B` (18.5%); center `£82,650` 18px/700 `#0F2544` + `Total` 12px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, right `£67,320 (81.5%)` 13px `#4A5B75`.
- Divider `1px #F2F5FA`, then centred link `View detailed report →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `25px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Raise Service Charge Demand, Record Payment (£ icon), Upload Expenses, Generate Statements, View Arrears Report, Manage Properties.

**Upcoming Deadlines** — title + `View Calendar` link. Rows: date chip `38px` square radius `8px` tinted (day 16px/700 + `AUG` 10px) accent red `#E2564F` / amber `#D98324` / violet `#7C5CE0`; title 13.5px/600 `#1B3358`, property 12.5px `#7A8AA3`; right `In 5 days` 12.5px/600 accent — Service Charge Demand - Q3 · Maple Court; Service Charge Review Meeting · Oak Heights; Annual Accounts Submission · Riverside Apartments. Footer `+ View all deadlines` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Service Charge Trend
- Title 17px/600 + `This Financial Year` select `34px` radius `8px` border `#E2E8F2`.
- Two area lines: Budgeted `#2E5BFF`, Collected `#16A34A`, 2.5px stroke, circular white-fill markers, soft gradient fills.
- Y axis `£0–£100K` steps `£20K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Legend below centred: swatch + `Budgeted` / `Collected` 12.5px `#4A5B75`.

### Arrears Summary
- Donut left ~`120px`, thickness `28px`, gapped arcs: `#E2564F`, `#F59E0B`, `#FACC15`, `#22C55E`.
- Legend right: dot `9px` + label 13px `#33455F`, right `£5,120 (33%)` 13px `#4A5B75` — Over 90 Days £5,120 (33%), 61 - 90 Days £4,380 (28%), 31 - 60 Days £3,270 (21%), Up to 30 Days £2,560 (16%).
- Divider then `Total Arrears` 13.5px `#33455F` + `£15,330` 15px/700 `#0F2544`.

### Expense Overview (This Month)
- Title 16px/600 + `(This Month)` 13px `#6B7A94`.
- Donut left ~`120px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#22C55E`, `#F97316`, `#8B5CF6`, `#14B8A6`.
- Legend right: Repairs & Maintenance £5,420 (33%), Cleaning £3,250 (20%), Insurance £2,180 (13%), Management Fees £2,950 (18%), Other Expenses £2,640 (16%).
- Divider then `Total Expenses` 13.5px `#33455F` + `£16,440` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, yellow `#FACC15`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; bottom row 2 + 1; table hides `Financial Year`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (property, collection bar, status); bottom cards single column.
