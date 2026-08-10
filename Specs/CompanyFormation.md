# Company Formation Screen — Look & Feel

Same shell as `BusinessStartup.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Company Formation`.

## Topbar
- Title `Company Formation` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Company Formation` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Fast, compliant and hassle-free company formation services.` 14px `#6B7A94`.
- Button right: `+ New Formation Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Month)` / `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Enquiries | 68 | +14 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| In Progress | 28 | +7 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Completed (This Month) | 22 | +5 this month `#16A34A` | `#F59E0B` / `#FEF2E3` (tick icon) |
| Companies Formed (This Year) | 146 | +18.2% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` (building icon) |
| Avg. Formation Time | 3.2 Days | -0.6 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| Success Rate | 96% | +2% vs last month `#16A34A` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Enquiry Source (`All Sources`), Company Type (`All Types`), Status (`All Status`), Jurisdiction (`All Jurisdictions`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Company Formation Progress table
- Header: `Company Formation Progress` 17px/600 + search `260px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by company name or client...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Company Name · Company Type · Jurisdiction · Stage · Status · Enquiry Date · Actions`.
- Rows height `48px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + ref `ENQ-1001` 11.5px `#7A8AA3`.
- Company Name / Company Type (`Private Limited`) / Jurisdiction (`UK`) / Enquiry Date 12.5px `#4A5B75`.
- **Stage pill** radius `999px`, 11.5px/600, padding `3px 11px` — Incorporation `#1D4ED8` on `#E8F0FE`; Name Reservation `#6D28D9` on `#F1EAFD`; Documents `#B45309` on `#FEF3DE`; Review `#C2410C` on `#FEEDE0`; Completed `#15803D` on `#E6F6EC`.
- **Status pill** same shape — In Progress `#1D4ED8` on `#E8F0FE`; Pending `#B45309` on `#FEF3DE`; Completed `#15803D` on `#E6F6EC`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 68 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Company Formation Overview (This Year)** — header 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#14B8A6`, `#F59E0B`, `#8B5CF6`, `#22C55E`; center `146` 22px/700 `#0F2544` + `Total` 12px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, right `58 (39.7%)` 13px `#4A5B75` — Incorporation 58 (39.7%), Name Reservation 28 (19.2%), Documents 22 (15.1%), Review 18 (12.3%), Completed 20 (13.7%).
- Divider `1px #F2F5FA`, then centred link `View detailed report →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `23px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: New Formation Enquiry, Company Name Availability Check, Incorporation Package Wizard, Upload Documents, Track Application, View All Companies.

**Upcoming Tasks** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG` 10px) accent blue `#2E5BFF` / green `#16A34A` / violet `#7C5CE0` / amber `#D98324`; title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `In 2 days` 12.5px/600 accent — Document Review · John Miller; Incorporation Filing · GreenBite Café Ltd; Name Reservation Expiry · AK Consultancy Ltd; Information Request · Bright Learning Academy Ltd. Footer `+ View all tasks →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Formation Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line `#2E5BFF`, 2.5px stroke, circular white-fill markers, gradient fill `rgba(46,91,255,.12) → transparent`.
- Y axis `0–40` steps of `10`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Below: two bordered stat boxes side by side, radius `10px`, border `1px #EDF1F7`, padding `12px`, centred — `Total Enquiries` 12.5px `#6B7A94` / `68` 20px/700; `Companies Formed` / `146`.

### Companies by Type (This Year)
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#2E5BFF` (dominant), `#22C55E`, `#F59E0B`, `#8B5CF6`, `#14B8A6`.
- Legend right: Private Limited 104 (71.2%), Limited by Guarantee 18 (12.3%), LLP 12 (8.2%), Public Limited 8 (5.5%), Others 4 (2.8%).
- Divider then `Total Companies` 13.5px `#33455F` + `146` 15px/700 `#0F2544`.

### Top Jurisdictions (This Year)
- Rows: label 13px `#33455F` left (fixed ~`60px`), horizontal bar centre — fill height `7px` radius `4px`, width proportional, colours `#2E5BFF`, `#22C55E`, `#14B8A6`, `#22C55E`, `#3730A3`; right `128 (87.7%)` 12.5px `#4A5B75`.
- UK 128 (87.7%), Ireland 6 (4.1%), USA 5 (3.4%), UAE 4 (2.7%), Others 3 (2.1%).
- Divider then `Total Companies` 13.5px `#33455F` + `146` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, indigo `#3730A3`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Jurisdiction`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, stage, status); bottom cards single column; trend stat boxes stack.
