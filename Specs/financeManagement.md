# Financial & Management Accounts Screen — Look & Feel

Same shell as `bookkeeping.md` / `payroll-advice.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Financial & Management Accounts` (wraps to two lines, gold gradient block taller).

## Topbar
- Title `Financial & Management Accounts` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Financial & Management Accounts` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Clear insights. Smarter decisions. Stronger business performance.` 14px `#6B7A94`.
- Button right: `+ New Report / Account` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94` with inline `(This Month)` 12px `#8494AC`; value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 96 | +8 this month | `#3B82F6` / `#EAF2FE` |
| Reports Generated | 78 | +12 this month | `#16A34A` / `#E8F6EC` |
| This Month Revenue | £96,450 | +14.3% vs last month | `#F59E0B` / `#FEF2E3` |
| Gross Profit (This Month) | £38,760 | 40.2% of Revenue | `#8B5CF6` / `#F1ECFE` |
| Year to Date Profit | £186,320 | +21.6% vs last year | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Client (`All Clients`), Financial Year (`2024 - 2025` — pre-selected, text `#33455F`), Report Type (`All Types`), Period (`Select Period`, calendar icon), Status (`All Status`).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Reports table
- Header: `Financial & Management Reports` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or report...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Report Type · Period / Year · Status · Generated On · Actions`.
- Rows height `50px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `32px` initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 14px/600 `#1B3358`.
- Report Type values: Management Accounts, Financial Statements, Cash Flow Statement, Profit & Loss Statement, Balance Sheet — 13.5px `#4A5B75`.
- Status pill radius `999px`, 12px/600, padding `4px 12px` — Completed `#15803D` on `#E6F6EC`; In Progress `#1D4ED8` on `#E8F0FE`; Pending Review `#B45309` on `#FEF3DE`; Draft `#6D28D9` on `#F1EAFD`.
- Actions: eye + **download** buttons `28px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 78 entries` 13px `#6B7A94`; pagination squares `34px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Financial Overview (This Month)** — header 16px/600 + `(This Month)` 13px `#6B7A94` + `View All` link `#2E5BFF` 13px. Five rows, divider `1px #F2F5FA`, height `37px`: circular icon `26px` tinted + label 13.5px `#33455F`, right amount 13.5px/600 `#0F2544`, far-right delta 12.5px/600 `#16A34A`.
- Revenue £96,450 +14.3% (blue `#2E5BFF`)
- Cost of Sales £57,690 +9.8% (amber `#F59E0B`)
- Gross Profit £38,760 +21.1% (green `#16A34A`)
- Operating Expenses £22,340 +6.2% (red `#E2564F`)
- Net Profit £16,420 +26.4% (violet `#8B5CF6`)

**Quick Actions** — title 16px/600; rows `24px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Generate Management Report, Generate Financial Statements, Cash Flow Projection, Variance Analysis, Compare Periods, Export Data.

**Upcoming Reports** — title + `View Calendar` link. Rows: date chip `40px` square radius `8px` tinted (day 16px/700 + `AUG` 10px uppercase) in accent (red `#E2564F`, amber `#D98324`, violet `#7C5CE0`); title 13.5px/600 `#1B3358` (e.g. `Management Accounts - Jul 2025`), client 12.5px `#7A8AA3`; right `In 6 days` 12.5px/600 accent. Footer `+ View all scheduled reports` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Profit & Loss Trend
- Title 17px/600 + `This Year` select `34px` radius `8px` border `#E2E8F2`.
- Two area lines: Revenue `#2E5BFF`, Net Profit `#16A34A`, 2.5px stroke, circular markers, soft gradient fills.
- Y axis `£0–£60K` steps `£15K`, 11.5px `#8494AC`; X axis Feb–Aug; gridlines `#EEF2F8`.
- Legend below centred: line swatch + `Revenue` / `Net Profit` 12.5px `#4A5B75`.

### Expense Breakdown (This Month)
- Title 16px/600 + `(This Month)` 13px `#6B7A94`.
- Donut left ~`130px`, thickness `28px`, gapped arcs: `#8B5CF6`, `#22C55E`, `#F59E0B`, `#2E5BFF`, `#E2564F`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent right-aligned `#6B7A94` — Staff Costs £9,250 41%, Rent & Rates £4,850 22%, Office Expenses £3,780 17%, Professional Fees £2,950 13%, Other Expenses £1,510 7%.
- Divider then `Total Expenses` 13.5px `#33455F` + `£22,340` 15px/700 `#0F2544`.

### Key Ratios (This Month)
- Rows height `41px`, divider `1px #F2F5FA`: small chart/clock icon `#16A34A`-tinted + label 13.5px `#33455F`, right value 13.5px/600 `#0F2544`, delta 12.5px/600 `#16A34A`.
- Gross Profit Margin 40.2% +4.1%; Net Profit Margin 17.0% +3.2%; Current Ratio 2.35:1 +0.18; Debtor Days 32 Days -3 Days.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`, amber `#F59E0B`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; bottom row 2 + 1.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards; all bottom cards single column.
