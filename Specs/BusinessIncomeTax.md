# Business Income Tax Screen — Look & Feel

Same shell as `PersonalIncomeTax.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Business Income Tax`.

## Topbar
- Title `Business Income Tax` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Business Income Tax` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Smart tax planning. Accurate filing. Minimum tax liability. Maximum savings.` 14px `#6B7A94`.
- Button right: `+ New Tax Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 96 | +12 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| Returns Filed (This Year) | 72 | +9 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Tax Payable (This Year) | £192,680 | +14.7% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| Tax Saved (This Year) | £48,230 | +16.2% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` |
| Avg. Tax Turnaround | 6.1 Days | -1.2 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| In Progress | 18 | `View details` link `#2E5BFF` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Tax Year (`2024 - 2025`, pre-selected), Client Type (`All Types`), Return Status (`All Status`), Industry (`All Industries`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Business Income Tax Overview table
- Header: `Business Income Tax Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or UTR...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client Name · UTR · Business Type · Profit Before Tax · Tax Payable · Status · Last Updated · Actions`.
- Rows height `48px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + `UTR: 1234567890` 11.5px `#7A8AA3`.
- UTR / Business Type (`Sole Trader`, `Partnership`, `Limited Company`) / Last Updated 12.5px `#4A5B75`.
- Profit Before Tax 12.5px/600 `#33455F`; **Tax Payable column in green** `#16A34A` 12.5px/600.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`; Refund Issued `#15803D` on `#E6F6EC`.
- Actions: eye + download (pencil on in-progress rows) `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 96 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Tax Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`128px`, thickness `28px`, gapped arcs: `#22C55E` (61.4%), `#2E5BFF` (18%), `#F59E0B` (12.9%), `#8B5CF6` (7.7%); center `£192,680` 17px/700 `#0F2544` + `Total Tax Payable` 11px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — Tax Paid £118,450 (61.4%), In Progress £34,600 (18.0%), Pending £24,780 (12.9%), In Review £14,850 (7.7%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `25px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New Tax Enquiry, Upload Documents, Business Income Tax Checklist, Expense Categorisation Tool, Tax Liability Calculator, View All Clients.

**Upcoming Deadlines** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG`/`SEP`/`JAN`/`JUL` 10px) accent red `#E2564F` / amber `#D98324` / green `#16A34A` / violet `#7C5CE0`; title 13.5px/600 `#1B3358`, tax-year sub 12.5px `#7A8AA3`; right `In 23 days` 12.5px/600 accent — Business Income Tax Return (2024 - 2025); Payment on Account (1st) (2025 - 2026); Self Assessment Deadline (2024 - 2025); Payment on Account (2nd) (2025 - 2026). Footer `+ View all deadlines` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Tax Payable Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94` + `This Financial Year` select right `32px` radius `8px` border `#E2E8F2`.
- Single area line `#2E5BFF`, 2.5px stroke, circular white-fill markers, gradient fill `rgba(46,91,255,.12) → transparent`.
- Y axis `£0–£40K` steps `£10K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Divider then footer row: left `Total Tax Payable` 12.5px `#6B7A94` over `£192,680` 18px/700 `#0F2544`; right `+14.7% vs last year` 12.5px/600 `#16A34A`.

### Business Type Distribution
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#F59E0B`, `#8B5CF6`, `#A78BFA`.
- Legend right: dot `9px` + label 13px `#33455F`, count + percent 13px `#4A5B75` — Sole Trader 42 (43.8%), Limited Company 28 (29.2%), Partnership 16 (16.7%), LLP 6 (6.3%), Others 4 (6.0%).
- Divider then `Total Clients` 13.5px `#33455F` + `96` 15px/700 `#0F2544`.

### Top Industries (This Year)
- Rows: label 13px `#33455F` left (fixed ~`80px`), horizontal bar centre — fill `#2E5BFF`, height `7px`, radius `4px`, width proportional; right `22 (22.9%)` 12.5px `#4A5B75`.
- Retail 22 (22.9%), Construction 18 (18.8%), IT & Services 16 (16.7%), Consultancy 12 (12.5%), Healthcare 10 (10.4%), Others 18 (18.8%).
- Divider then `Total Clients` 13.5px `#33455F` + `96` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `UTR` column (kept under client name).
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, business type, tax payable, status); bottom cards single column.
