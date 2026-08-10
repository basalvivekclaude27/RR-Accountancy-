# Business Plans Screen — Look & Feel

Same shell as `CompanyFormation.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Business Plans`.

## Topbar
- Title `Business Plans` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Business Plans` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `Strategic plans for sustainable growth. Clear roadmap. Better decisions. Stronger future.` 14px `#6B7A94`.
- Button right: `+ New Business Plan` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Month)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px `#16A34A`.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Plans | 52 | +8 this month | `#3B82F6` / `#EAF2FE` (doc icon) |
| In Progress | 18 | +4 this month | `#16A34A` / `#E8F6EC` (clipboard icon) |
| Completed (This Month) | 12 | +3 this month | `#F59E0B` / `#FEF2E3` (tick icon) |
| Active Plans | 34 | +12.5% vs last month | `#8B5CF6` / `#F1ECFE` (chart icon) |
| Success Rate | 87% | +5% vs last month | `#2E5BFF` / `#EAF2FE` (pie icon) |
| Revenue Potential | £2.45M | +15.3% vs last month | `#14B8A6` / `#E4F7F5` (£ icon) |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~340px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Client / Business (`All Clients`), Plan Type (`All Types`), Plan Status (`All Status`), Industry (`All Industries`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Business Plans Overview table
- Header: `Business Plans Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client or plan name...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Plan Name · Plan Type · Status · Last Updated · Revenue Potential · Actions`.
- Rows height `50px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + ref `ENQ-1001` 11.5px `#7A8AA3`.
- Plan Name / Plan Type / Last Updated 12.5px `#4A5B75`; Revenue Potential 12.5px/600 `#33455F`.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — In Progress `#1D4ED8` on `#E8F0FE`; Completed `#15803D` on `#E6F6EC`; Pending Review `#B45309` on `#FEF3DE`; (also On Hold `#6D28D9` on `#F1EAFD`, Not Started `#5A6B85` on `#F1F4F9`).
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 52 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Plan Status Distribution** — title 16px/600.
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#F59E0B`, `#8B5CF6`, `#14B8A6`; center `52` 22px/700 `#0F2544` + `Total Plans` 11.5px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, count 13px `#4A5B75`, percent right-aligned `#6B7A94` — In Progress 18 (34.6%), Completed 12 (23.1%), Pending Review 8 (15.4%), On Hold 6 (11.5%), Not Started 8 (15.4%).
- Divider `1px #F2F5FA`, centred link `View full report →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `23px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Create New Business Plan, Business Plan Template Library, Financial Projection Builder, SWOT Analysis Tool, Download Business Plan Template, View All Business Plans.

**Upcoming Reviews** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG` 10px) accent blue `#2E5BFF` / green `#16A34A` / amber `#D98324` / violet `#7C5CE0`; title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `In 2 days` 12.5px/600 accent — 5 Year Growth Plan Review · John Miller; Investor Pitch Deck Review · Lisa Roberts; Expansion Plan Review · Amit Kumar; Operational Plan Review · David Wilson. Footer `+ View all reviews` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Revenue Potential Trend
- Title 17px/600 + `This Financial Year` select `34px` radius `8px` border `#E2E8F2`.
- Single area line `#2E5BFF`, 2.5px stroke, circular white-fill markers, gradient fill `rgba(46,91,255,.12) → transparent`.
- Y axis `£0–£500K` steps `£100K`, 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Legend below centred: line swatch + `Revenue Potential` 12.5px `#4A5B75`.
- Divider then `Total Revenue Potential` 13.5px `#33455F` + `£2.45M` 16px/700 `#0F2544`.

### Plans by Type
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#14B8A6`, `#F59E0B`, `#F97316`, `#8B5CF6`.
- Legend right: dot `9px` + label 13px `#33455F`, count + percent 13px `#4A5B75` — Growth Plan 18 (34.6%), Startup Plan 12 (23.1%), Expansion Plan 8 (15.4%), Operational Plan 6 (11.5%), Others 8 (15.4%).
- Divider then `Total Plans` 13.5px `#33455F` + `52` 15px/700 `#0F2544`.

### Top Industries
- Rows: label 13px `#33455F` left (fixed ~`80px`), horizontal bar centre — fill `#2E5BFF`, height `7px`, radius `4px`, width proportional; right `16 (30.8%)` 12.5px `#4A5B75`.
- Technology 16 (30.8%), Retail 10 (19.2%), Healthcare 8 (15.4%), Construction 6 (11.5%), Others 12 (23.1%).
- Divider then `Total Plans` 13.5px `#33455F` + `52` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Plan Type`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, plan name, status, revenue); bottom cards single column.
