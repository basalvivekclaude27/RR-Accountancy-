# Business Startup Screen — Look & Feel

Same shell as `ServiceCharge.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Business Startup`.

## Topbar
- Title `Business Startup` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Business Startup` `#6B7A94`.

## Page header
- Title 24px/700 `#0F2544`; sub `From idea to launch – we help you start your business the right way.` 14px `#6B7A94`.
- Button right: `+ New Startup Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94`; value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Enquiries | 56 | +12 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| Active Startups | 24 | +6 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Startups Launched | 18 | +4 this month `#16A34A` | `#F97316` / `#FEF0E6` (rocket icon) |
| In Progress | 24 | `View details` link `#8B5CF6` | `#8B5CF6` / `#F1ECFE` (clock icon) |
| Success Rate | 75% | +8% vs last year `#16A34A` | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~330px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Enquiry Source (`All Sources`), Startup Stage (`All Stages`), Business Type (`All Types`), Status (`All Status`), Assigned To (`All Staff`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px text).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### Startup Enquiries & Progress table
- Header: `Startup Enquiries & Progress` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or business...`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Business Name · Business Type · Stage · Status · Enquiry Date · Actions`.
- Rows height `50px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `32px` initials circle (2 letters, e.g. `JM`) on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + person name 13.5px/600 `#1B3358` + ref `ENQ-1001` 12px `#7A8AA3`.
- Business Name / Business Type / Enquiry Date 13px `#4A5B75`.
- **Stage pill** radius `999px`, 11.5px/600, padding `3px 11px` — Business Plan `#B45309` on `#FEF3DE`; Company Formation `#1D4ED8` on `#E8F0FE`; Registration `#6D28D9` on `#F1EAFD`; Document Setup `#C2410C` on `#FEEDE0`; Launched `#15803D` on `#E6F6EC`.
- **Status pill** same shape — In Progress `#1D4ED8` on `#E8F0FE`; Pending Info `#B45309` on `#FEF3DE`; New `#5A6B85` on `#F1F4F9`; Completed `#15803D` on `#E6F6EC`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 24 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 › »`.

### Right rail
**Startup Overview (This Month)** — header 16px/600 + `(This Month)` 13px `#6B7A94`.
- Donut left ~`120px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#14B8A6`, `#F59E0B`, `#8B5CF6`, `#22C55E`.
- Legend right: dot `9px` + label 13px `#33455F`, right `24 (30%)` 13px `#4A5B75` — Business Plan 24 (30%), Company Formation 18 (22%), Registration 12 (15%), Document Setup 10 (12%), Launched 18 (21%).
- Divider `1px #F2F5FA`, then `Total` 13.5px `#6B7A94` + `82` 14px/700 `#0F2544`.

**Quick Actions** — title 16px/600; rows `24px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Create New Enquiry, Business Name Check, Company Formation Wizard, Download Startup Checklist, Upload Documents, View All Enquiries.

**Upcoming Tasks** — title + `View All` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG` 10px) accent blue `#2E5BFF` / green `#16A34A` / violet `#7C5CE0` / amber `#D98324`; title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right `In 3 days` 12.5px/600 accent — Collect Documents · GreenBite Café; Business Plan Review · Miller Tech Solutions; Company Registration · AK Consultancy Ltd.; Follow Up Call · Bright Learning Academy. Footer `+ View all tasks` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Startup Pipeline
- Funnel chart left ~`130px`: 5 stacked trapezoid bands, `4px` vertical gaps, narrowing downward — `#2E5BFF`, `#22C55E`, `#F59E0B`, `#F97316`, `#8B5CF6`.
- Legend right: dot `9px` + label 13px `#33455F`, right count 13px/600 `#33455F` — New Enquiries 56, Initial Discussion 34, Planning 24, Documentation 18, Launched 18.
- Divider then `Conversion Rate` 13.5px `#33455F` + `32%` 15px/700 `#0F2544`.

### Popular Services (This Month)
- Title 16px/600 + `(This Month)` 13px `#6B7A94`.
- Rows: label 13px `#33455F` left; horizontal bar centre — track transparent, fill height `6px` radius `3px`, width proportional, colours `#2E5BFF`, `#22C55E`, `#F59E0B`, `#8B5CF6`, `#14B8A6`; right `32 (39%)` 12.5px `#4A5B75`.
- Company Formation 32 (39%), Business Plan 20 (24%), Registration & Compliance 14 (17%), Document Setup 10 (12%), Others 6 (7%).
- Divider then `Total Services Used` 13.5px `#33455F` + `82` 15px/700 `#0F2544`.

### Enquiries by Source (This Month)
- Donut left ~`120px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#22C55E`, `#F59E0B`, `#8B5CF6`.
- Legend right: Website 28 (50%), Referral 14 (25%), Advertisement 8 (14%), Social Media 6 (11%).
- Divider then `Total` 13.5px `#33455F` + `56` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, orange `#F97316`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2; bottom row 2 + 1; table hides `Business Type`.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, stage, status); bottom cards single column; funnel scales to full width.
