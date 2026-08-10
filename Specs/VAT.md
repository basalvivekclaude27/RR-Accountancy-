# Value Added Tax (VAT) Screen — Look & Feel

Same shell as `CorporateTax.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Value Added Tax (VAT)`.

## Topbar
- Title `Value Added Tax (VAT)` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Value Added Tax (VAT)` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Stay VAT compliant. Accurate returns. Maximum savings. Complete peace of mind.` 14px `#6B7A94`.
- Button right: `+ New VAT Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 68 | +9 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| VAT Returns Filed (This Year) | 54 | +7 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| VAT Payable (This Year) | £118,750 | +13.6% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| VAT Reclaimed (This Year) | £76,320 | +16.4% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` |
| Avg. Refund Time | 18.6 Days | -3.2 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| In Progress | 16 | `View details` link `#2E5BFF` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~350px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): VAT Period (`All Periods`), Client Type (`All Types`), Return Status (`All Status`), Scheme Type (`All Schemes`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### VAT Returns Overview table
- Header: `VAT Returns Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or VAT No.`.
- Head row bg `#FAFBFD`, 12px/600 `#5A6B85`: `Client Name · VAT No. · VAT Period · Sales (Ex VAT) · Purchases (Ex VAT) · VAT Payable / (Refund) · Status · Filed On · Actions`.
- Rows height `48px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + company 13px/600 `#1B3358` + scheme sub-label `Standard Rate` / `Flat Rate Scheme` 11.5px `#7A8AA3`.
- VAT No. `GB 123 4567 89` 12.5px `#4A5B75`.
- VAT Period two lines: `Apr - Jun 2025` 12.5px `#4A5B75` + `(Q1 2025/26)` 11.5px `#7A8AA3`.
- Sales / Purchases 12.5px `#4A5B75`, `—` `#9AA7BC` when empty.
- **VAT Payable / (Refund) in red** `#E2564F` 12.5px/600 (payable amounts); refund rows shown in same column.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`; Refund Issued `#15803D` on `#E6F6EC`.
- Filed On: date 12.5px `#4A5B75` or `—` `#9AA7BC`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 68 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, with ellipsis: `« ‹ 1 2 3 4 5 … 12 »`.

### Right rail
**VAT Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`128px`, thickness `28px`, gapped arcs: `#E2564F` (48.2%), `#22C55E` (31.0%), `#F59E0B` (12.7%), `#8B5CF6` (8.1%); center `£118,750` 17px/700 `#0F2544` + `Net VAT Payable` 11px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — VAT Payable £118,750 (48.2%), VAT Reclaimed £76,320 (31.0%), In Progress £31,460 (12.7%), Pending / In Review £20,140 (8.1%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `25px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New VAT Enquiry, Upload Purchase Invoices, VAT Return Checklist, VAT Calculation Tool, Check VAT Registration, View All Clients.

**Upcoming VAT Deadlines** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG`/`SEP` 10px) accent red `#E2564F` / amber `#D98324`; title `VAT Return – Apr to Jun 2025` 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `Due in 2 days` 12.5px/600 accent (red for ≤9 days, amber beyond) — John Miller Ltd; GreenBite Café Ltd; AK Consultancy Ltd; Wilson Trading Ltd (Jul to Sep 2025). Footer `+ View all deadlines →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### VAT Payable Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line `#2E5BFF`, 2.5px stroke, circular white-fill markers with blue ring, gradient fill `rgba(46,91,255,.12) → transparent`.
- Y axis `£0–£20K` steps `£5K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Below: two bordered stat boxes side by side, radius `10px`, border `1px #EDF1F7`, padding `12px`, centred — `Total VAT Payable` 12.5px `#6B7A94` / `£118,750` 18px/700 `#0F2544`; second box `+13.6% vs last year` 13px/600 `#16A34A`.

### VAT by Return Status (This Year)
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E`, `#F59E0B`, `#2E5BFF`, `#8B5CF6`, `#7C3AED`, `#94A3B8`.
- Legend right: dot `9px` + label 13px `#33455F`, count + percent 13px `#4A5B75` — Filed 54 (44.4%), In Progress 16 (13.2%), In Review 13 (10.7%), Pending Docs 9 (7.4%), Refund Issued 12 (9.9%), Others 18 (14.4%).
- Divider then `Total Returns` 13.5px `#33455F` + `122` 15px/700 `#0F2544`.

### Top VAT Reclaims (This Year)
- Rows height `29px`, divider `1px #F2F5FA`: client name 13px `#33455F` left, amount 13px/600 `#0F2544` right — AK Consultancy Ltd £18,420; John Miller Ltd £15,860; Wilson Trading Ltd £12,340; Bright Learning Academy £8,950; Thakkar Events Ltd £7,980.
- Footer centred link `View all reclaims →` 13px `#2E5BFF`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, teal `#14B8A6`, violet `#8B5CF6`/`#7C3AED`, red `#E2564F`, slate `#94A3B8`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Purchases (Ex VAT)`.
- `<1280px`: right rail drops below table; table also hides `Sales (Ex VAT)`.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, VAT period, payable, status); bottom cards single column; trend stat boxes stack.
