# Budgeting & Forecasting Screen — Look & Feel

Same shell as `financeManagement.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Budgeting & Forecasting`.

## Topbar
- Title `Budgeting & Forecasting` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Budgeting & Forecasting` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Plan today. Achieve tomorrow. Stay ahead with accurate budgets and smart forecasts.` 14px `#6B7A94`.
- Button right: `+ New Budget / Forecast` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94` with inline `(This Month)` 12px `#8494AC`; value 26px/700 `#0F2544`; footnote 12.5px `#16A34A`.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Active Budgets | 24 | +4 this month | `#3B82F6` / `#EAF2FE` |
| Forecasts Generated | 18 | +3 this month | `#16A34A` / `#E8F6EC` |
| Total Budgeted Amount | £1,254,800 | +15.6% vs last year | `#F59E0B` / `#FEF2E3` |
| Forecasted Revenue | £1,487,600 | +18.2% vs last year | `#8B5CF6` / `#F1ECFE` |
| Variance (This Month) | £42,750 | +8.7% vs last month | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Client (`All Clients`), Budget/Forecast Type (`All Types`), Financial Year (`2024 - 2025`, pre-selected), Period (`All Periods`, calendar icon), Status (`All Status`).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Budgets & Forecasts table
- Header: `Budgets & Forecasts` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or budget...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Type · Financial Year · Period · Budgeted Amount · Status · Last Updated · Actions`.
- Rows height `45px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13.5px/600 `#1B3358`.
- Type values: Annual Budget, Forecast, Budget Revision — 13px `#4A5B75`.
- Period values: `Yearly`, `Q1 (Apr - Jun)`, `Q2 (Jul - Sep)`, `Q3 (Oct - Dec)`; Budgeted Amount 13px/600 `#33455F`.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Approved `#15803D` on `#E6F6EC`; In Progress `#1D4ED8` on `#E8F0FE`; Pending Review `#B45309` on `#FEF3DE`; Completed `#15803D` on `#E6F6EC`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 24 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 › »`.

### Right rail
**Budget vs Actual (This Month)** — header 16px/600 + `(This Month)` 13px `#6B7A94` + `View All` `#2E5BFF` 13px.
- Donut left ~`120px`, thickness `26px`, two arcs: Actual `#2E5BFF` (68%), Budgeted `#22C55E` (32%); center `£42,750` 18px/700 `#0F2544` + `Variance` 12px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, right `£96,250 (68%)` 13px `#4A5B75`.
- Below, divider `1px #F2F5FA`, three rows height `27px`: label 13px `#33455F` left, value 13px/600 `#0F2544` right — Budgeted Amount £149,750; Actual Amount £96,250; Variance £42,750 with `(+28.5%)` 12px `#16A34A`.

**Quick Actions** — title 16px/600; rows `27px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Create New Budget, Create Forecast, Budget vs Actual Report, Forecast vs Actual Report, Download Budget Template, View All Reports.

**Upcoming Deadlines** — title + `View Calendar` link. Rows: date chip `38px` square radius `8px` tinted (day 16px/700 + `AUG` 10px) in accent (red `#E2564F`, amber `#D98324`, violet `#7C5CE0`); title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `In 6 days` 12.5px/600 accent — Q2 Forecast Submission, Budget Review Meeting, Annual Budget Finalization. Footer `+ View all deadlines` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Budget vs Actual Trend
- Title 17px/600 + `This Year` select `34px` radius `8px` border `#E2E8F2`.
- Two area lines: Budgeted `#2E5BFF`, Actual `#16A34A`, 2.5px stroke, circular white-fill markers, soft gradient fills.
- Y axis `£0–£200K` steps `£50K`, 11.5px `#8494AC`; X axis Apr→Mar (12 months); gridlines `#EEF2F8`.
- Legend below centred: line swatch + `Budgeted` / `Actual` 12.5px `#4A5B75`.

### Budget by Category (This Year)
- Donut left ~`130px`, thickness `30px`, gapped arcs: `#2E5BFF`, `#14B8A6`, `#F97316`, `#8B5CF6`, `#F59E0B`.
- Legend right: dot `9px` + label 13px `#33455F`, right `£420,000 (33%)` 13px `#4A5B75` — Staff Costs £420,000 (33%), Operations £310,000 (25%), Marketing £160,000 (13%), Professional Fees £140,000 (11%), Other Expenses £224,800 (18%).
- Divider then `Total Budgeted` 13.5px `#33455F` + `£1,254,800` 15px/700 `#0F2544`.

### Forecast Accuracy
- Semicircular gauge ~`170px` wide: arc thickness `18px`, segmented red `#E2564F` → amber `#F59E0B` → yellow-green `#A3C93A` → green `#22C55E`; dark needle `#1B3358` with hub dot; scale labels `0%` / `50%` / `100%` 11px `#8494AC`.
- Value `86%` 26px/700 `#0F2544` centred under needle.
- Caption `Good Accuracy` 14px/600 `#16A34A`; helper `Your forecasts are performing well within acceptable range.` 12.5px `#6B7A94`, centred.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; bottom row 2 + 1.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards; bottom cards single column; trend chart shows 6 months.
