# Corporate Tax Screen — Look & Feel

Same shell as `BusinessIncomeTax.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Corporate Tax`.

## Topbar
- Title `Corporate Tax` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Corporate Tax` `#6B7A94`.

## Page header
- **No page title / subtitle block** on this screen — content starts with the action button row.
- Button right-aligned: `+ New Corporate Tax Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 78 | +10 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| CT Returns Filed (This Year) | 65 | +8 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Corporation Tax Payable | £156,820 | +12.8% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| Tax Saved (This Year) | £32,450 | +14.7% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` (piggy icon) |
| Avg. Processing Time | 5.4 Days | -1.3 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| In Progress | 14 | `View details` link `#2E5BFF` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Accounting Period (`2024 - 2025`, pre-selected), Client Type (`All Types`), Return Status (`All Status`), Industry (`All Industries`), Due Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Corporate Tax Overview table
- Header: `Corporate Tax Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or UTR...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client Name · UTR · Accounting Period · Taxable Profit · Tax Payable · Status · Due Date · Actions`.
- Rows height `48px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + company name 13px/600 `#1B3358` (e.g. `John Miller Ltd`) + `UTR: 1234567890` 11.5px `#7A8AA3`.
- UTR / Accounting Period (`01/04/2024 - 31/03/2025`) / Due Date 12.5px `#4A5B75`.
- Taxable Profit 12.5px/600 `#33455F`; **Tax Payable in green** `#16A34A` 12.5px/600.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 78 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Tax Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`128px`, thickness `28px`, gapped arcs: `#22C55E` (55.1%), `#2E5BFF` (22.1%), `#F59E0B` (14.2%), `#8B5CF6` (8.6%); center `£156,820` 17px/700 `#0F2544` + `Total Tax Payable` 11px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — Tax Paid £86,400 (55.1%), In Progress £34,600 (22.1%), Pending £22,300 (14.2%), In Review £13,520 (8.6%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `25px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New Corporate Tax Enquiry, Upload Financial Statements, Corporation Tax Checklist, Tax Computation Tool, Check CT Payment Account, View All Clients.

**Upcoming Deadlines** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `JUL`/`AUG`/`SEP` 10px) accent red `#E2564F` / amber `#D98324` / green `#16A34A` / violet `#7C5CE0`; title `Corporation Tax Return` 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `Due in 22 days` 12.5px/600 accent (red for nearest, amber onwards) — John Miller Ltd; Bright Learning Academy Ltd; GreenBite Café Ltd; AK Consultancy Ltd. Footer `+ View all deadlines →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Tax Payable Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line `#2E5BFF`, 2.5px stroke, circular white-fill markers, gradient fill `rgba(46,91,255,.12) → transparent`.
- Y axis `£0–£200K` steps `£50K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Below: two bordered stat boxes side by side, radius `10px`, border `1px #EDF1F7`, padding `12px`, centred — `Total Tax Payable` 12.5px `#6B7A94` / `£156,820` 18px/700 `#0F2544`; second box `+12.8%` 18px/700 `#16A34A` / `vs last year` 12px `#6B7A94`.

### CT Returns by Status (This Year)
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#8B5CF6`, `#F59E0B`, `#A78BFA`.
- Legend right: dot `9px` + label 13px `#33455F`, count + percent 13px `#4A5B75` — Filed 65 (53.3%), In Progress 14 (18.0%), In Review 11 (14.8%), Pending 7 (9.0%), Others 3 (3.9%).
- Divider then `Total Returns` 13.5px `#33455F` + `100` 15px/700 `#0F2544`.

### Top Industries (This Year)
- Rows: label 13px `#33455F` left (fixed ~`95px`), horizontal bar centre — fill `#2E5BFF`, height `7px`, radius `4px`, width proportional; right `24 (30.8%)` 12.5px `#4A5B75`.
- Professional Services 24 (30.8%), Retail & Wholesale 16 (20.5%), IT & Technology 12 (15.4%), Construction 9 (11.5%), Hospitality 6 (7.7%), Others 11 (14.1%).
- Divider then `Total Clients` 13.5px `#33455F` + `78` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Accounting Period`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, tax payable, status, due date); bottom cards single column; trend stat boxes stack.
