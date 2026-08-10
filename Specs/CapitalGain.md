# Capital Gain Tax Screen — Look & Feel

Same shell as `VAT.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Capital Gain Tax`.

## Topbar
- Title `Capital Gain Tax` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Capital Gain Tax` `#6B7A94`.

## Page header
- Title `Capital Gain Tax` 24px/700 `#0F2544`; sub `Expert guidance. Accurate reporting. Optimise your gains, legally.` 14px `#6B7A94`.
- Button right: `+ New CGT Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 42 | +6 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| CGT Reports Filed (This Year) | 36 | +5 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Total Gains (This Year) | £1,248,560 | +18.7% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| Total Tax Payable (This Year) | £248,750 | +15.9% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` (bar-chart icon) |
| Avg. Processing Time | 9.4 Days | -2.1 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| In Progress | 11 | `View details` link `#2E5BFF` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~175px` · table (fluid) · right rail `~350px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Tax Year (`2024 - 2025`, pre-selected), Client Type (`All Types`), Asset Type (`All Asset Types`), Report Status (`All Status`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### CGT Cases Overview table
- Header: `CGT Cases Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or UTR...`.
- Head row bg `#FAFBFD`, 12px/600 `#5A6B85`: `Client Name · UTR · Asset Type · Date of Disposal · Gain / (Loss) · Tax Payable · Status · Last Updated · Actions` (sort carets on UTR and Status).
- Rows height `46px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + `UTR: 1234567890` 11.5px `#7A8AA3`.
- UTR / Asset Type (`Residential Property`, `Shares`, `Buy to Let Property`, `Crypto Assets`) / Date of Disposal / Last Updated 12.5px `#4A5B75`.
- **Gain / (Loss)**: positive `£185,650` 12.5px/600 `#16A34A`; loss in brackets `(£12,750)` `#E2564F`.
- **Tax Payable**: `£37,130` 12.5px/600 `#E2564F`; zero shown as `£0`.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 42 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**CGT Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`128px`, thickness `28px`, gapped arcs: `#22C55E` (47.2%), `#2E5BFF` (27.6%), `#F59E0B` (12.9%), `#8B5CF6` (12.3%); center `£248,750` 17px/700 `#0F2544` + `Total Tax Payable` 11px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — Residential Property £117,450 (47.2%), Shares £68,660 (27.6%), Crypto Assets £32,140 (12.9%), Other Assets £30,500 (12.3%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `24px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New CGT Enquiry, Upload Disposal Details, CGT Calculation Tool, Annual Exempt Amount Checker, Relief & Allowance Guide, View All Clients.

**Upcoming CGT Deadlines** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `OCT`/`JAN`/`APR`/`JUL` 10px) accent red `#E2564F` / amber `#D98324` / green `#16A34A` / violet `#7C5CE0`; title `Report & Pay (31 Oct 2025)` 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `In 83 days` 12.5px/600 accent — John Miller; Sarah Patel; Amit Kumar; David Wilson. Footer `+ View all deadlines →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Gain / (Loss) Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line `#16A34A`, 2.5px stroke, circular white-fill markers with green ring, gradient fill `rgba(34,197,94,.12) → transparent`.
- Y axis `-£100K → £400K` steps `£100K` (zero line emphasised), 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Legend below centred: swatch + `Total Gain / (Loss)` 12px `#4A5B75`.
- Below: two bordered stat boxes, radius `10px`, border `1px #EDF1F7`, padding `12px` — `Total Gains (This Year)` 12px `#6B7A94` / `£1,248,560` 17px/700 `#0F2544`; second box `+18.7%` 16px/700 `#16A34A` / `vs last year` 11.5px `#6B7A94`.

### Gains by Asset Type (This Year)
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#F59E0B`, `#8B5CF6`.
- Legend right: dot `9px` + label 13px `#33455F`, amount + percent 13px `#4A5B75` — Residential Property £589,450 (47.2%), Shares £345,600 (27.6%), Crypto Assets £160,890 (12.9%), Other Assets £152,620 (12.3%).
- Divider then `Total Gains` 13.5px `#33455F` + `£1,248,560` 15px/700 `#0F2544`.

### CGT Status (This Year)
- Rows: label 13px `#33455F` left (fixed ~`110px`), horizontal bar centre — height `8px`, radius `4px`, width proportional, colour per status (`#22C55E`, `#2E5BFF`, `#F59E0B`, `#E2564F`, `#8B5CF6`, `#94A3B8`); right `36 (42.9%)` 12.5px `#4A5B75`.
- Filed 36 (42.9%), In Review 11 (13.1%), In Progress 11 (13.1%), Documents Pending 7 (8.3%), Awaiting Info 5 (6.0%), Draft 4 (4.8%).
- Divider then `Total Cases` 13.5px `#33455F` + `84` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, slate `#94A3B8`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Date of Disposal`.
- `<1280px`: right rail drops below table; table also hides `UTR` column.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, asset type, gain, tax, status); bottom cards single column; trend stat boxes stack.
