# Payroll Advice & Service Screen — Look & Feel

Same shell as `bookkeeping.md` / `accountpreparation.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Payroll Advice & Service`.

## Topbar
- Title `Payroll Advice & Service` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Payroll Advice & Service` `#6B7A94`.

## Page header
- Title `Payroll Advice & Service` 24px/700 `#0F2544`; sub `Hassle-free payroll management with accurate calculations, timely submissions and expert advice.` 14px `#6B7A94`.
- Button right: `+ New Payroll Run` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94` (secondary `(This Month)` 12px `#8494AC` inline); value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Employees | 58 | +3 this month | `#3B82F6` / `#EAF2FE` |
| Active Payrolls | 12 | +2 this month | `#16A34A` / `#E8F6EC` |
| Total Payroll Cost (This Month) | £28,540 | +12.6% vs last month | `#F59E0B` / `#FEF2E3` |
| Submissions Due | 3 | `View due dates` link | `#8B5CF6` / `#F1ECFE` |
| Year to Date Payroll | £284,750 | +18.4% vs last year | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~330px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Client (`All Clients`), Payroll Frequency (`All Frequency`), Payroll Month (`Select Month`, calendar icon), Status (`All Status`), Assigned To (`All Staff`).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`, text `#33455F`.

### Payroll Runs table
- Header: `Payroll Runs` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or payroll run...`, magnifier `#9AA7BC`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Payroll Month · Frequency · Employees · Net Pay · Status · Last Updated · Actions`.
- Rows height `52px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `32px` initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 14px/600 `#1B3358` (single line, no sub-ref).
- Payroll Month / Frequency / Last Updated 13.5px `#4A5B75`; Employees + Net Pay right-ish aligned 13.5px/600 `#33455F`.
- Status pill radius `999px`, 12px/600, padding `4px 12px` — Completed `#15803D` on `#E6F6EC`; In Progress `#1D4ED8` on `#E8F0FE`; Pending Approval `#B45309` on `#FEF3DE`; Paid `#15803D` on `#E6F6EC` (lighter tone).
- Actions: eye + pencil buttons `28px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 12 entries` 13px `#6B7A94`; pagination squares `34px`, active `#0B2A5B` white, controls `« ‹ 1 2 › »`.

### Right rail
**Payroll Summary (This Month)** — title 16px/600 + `(This Month)` 13px `#6B7A94`. Left donut ~`120px`, thickness `28px`, gapped arcs: `#2E5BFF` (dominant), `#22C55E`, `#F59E0B`, `#8B5CF6`, `#E2564F`. Legend right: dot `9px` + label 13px `#33455F`, right `£28,540 (60%)` 13px `#4A5B75` — Net Pay £28,540 (60%), Employer NI £5,420 (11%), Pension Contributions £4,380 (9%), Tax (PAYE) £6,150 (13%), Other Deductions £3,210 (7%). Divider `1px #EDF1F7`, then `Total Cost` `#6B7A94` + `£47,700` 14px/700 `#0F2544`.

**Quick Actions** — title 16px/600; rows `31px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Run New Payroll, Upload Timesheets, Upload RTI File, Pension Submission, HMRC Payment, Payroll Reports.

**Reminders** — title + `View All` `#2E5BFF` 13px. Rows: icon tile `34px` radius `8px` tinted blue / amber / violet; title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right date 12.5px/600 accent (`#E2564F`, `#D98324`, `#7C5CE0`) — RTI Submission Due · 10 Aug 2025; Pension Submission Due · 15 Aug 2025; PAYE Payment Due · 19 Aug 2025.

## Bottom row (2 cards)
### Payroll Cost Trend
- Title 17px/600 + `This Year` select right — height `34px`, radius `8px`, border `#E2E8F2`, 13px.
- Area line chart: stroke `#2E5BFF` 2.5px, circular white-fill markers with blue ring, fill gradient `rgba(46,91,255,.15) → transparent`.
- Y axis `£0–£50K` steps of `£10K`, labels 11.5px `#8494AC`; X axis Feb–Jul; gridlines `#EEF2F8`.

### Employee Distribution
- Donut left ~`130px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#22C55E`, `#F59E0B`, `#8B5CF6`.
- Legend right: dot `9px` + label 13.5px `#33455F`, right `34 (59%)` 13.5px `#4A5B75` — Full Time 34 (59%), Part Time 12 (21%), Contractor 8 (14%), Director 4 (6%).
- Divider then `Total` `#6B7A94` + `58` 14px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`, amber `#F59E0B`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; table hides `Frequency` column.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, net pay, status); bottom row single column.
