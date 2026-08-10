# Inheritance Tax Screen — Look & Feel

Same shell as `CapitalGain.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger, breadcrumb, bell + avatar). Active sidebar item = `Inheritance Tax` (last nav item).

## Topbar
- Title `Inheritance Tax` 20px/700 `#0B2A5B`.
- Breadcrumb 13px: `Dashboard` `#2E5BFF` › `Inheritance Tax` `#6B7A94`.

## Page header
- Title `Inheritance Tax` 24px/700 `#0F2544`; sub `Plan today. Protect tomorrow. Minimise tax. Preserve your legacy.` 14px `#6B7A94`.
- Button right: `+ New IHT Enquiry` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 22px`.

## KPI strip (6 cards)
Grid 6 × equal, gap `14px`, white, radius `12px`, border `1px #EDF1F7`, padding `14px`. Icon tile `44px` radius `10px` tinted; label 13px `#6B7A94` with inline `(This Year)` 11.5px `#8494AC`; value 24px/700 `#0F2544`; footnote 12px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 38 | +5 this month `#16A34A` | `#3B82F6` / `#EAF2FE` |
| IHT Returns / Plans (This Year) | 27 | +6 this month `#16A34A` | `#16A34A` / `#E8F6EC` |
| Est. Tax Liability (This Year) | £1,056,480 | +12.6% vs last year `#16A34A` | `#F59E0B` / `#FEF2E3` (£ icon) |
| Tax Saved (This Year) | £312,750 | +15.3% vs last year `#16A34A` | `#8B5CF6` / `#F1ECFE` (shield icon) |
| Avg. Processing Time | 7.8 Days | -1.4 days vs last month `#16A34A` | `#2E5BFF` / `#EAF2FE` (clock icon) |
| In Progress | 9 | `View details` link `#2E5BFF` | `#14B8A6` / `#E4F7F5` (pie icon) |

## Main grid
Three columns: filters `~175px` · table (fluid) · right rail `~350px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Tax Year (`2024 - 2025`, pre-selected), Client Type (`All Types`), Report Status (`All Status`), IHT Planning Type (`All Types`), Date Range (`01/04/2024 - 31/03/2025`, calendar icon, 12.5px).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white, border `1px #E2E8F2`.

### IHT Cases Overview table
- Header: `IHT Cases Overview` 17px/600 + search `250px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search by client name or UTR...`.
- Head row bg `#FAFBFD`, 12px/600 `#5A6B85`: `Client Name · UTR · Planning / Report Type · Estate Value · Nil Rate Band Available · Est. Tax Liability · Status · Last Updated · Actions` (Nil Rate Band header wraps to two lines).
- Rows height `45px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `30px` two-letter initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`) + name 13px/600 `#1B3358` + `UTR: 1234567890` 11.5px `#7A8AA3`.
- UTR / Planning Type (`IHT Return`, `IHT Planning`) / Estate Value / Nil Rate Band / Last Updated 12.5px `#4A5B75`.
- **Est. Tax Liability in red** `#E2564F` 12.5px/600.
- Status pill radius `999px`, 11.5px/600, padding `3px 11px` — Filed `#15803D` on `#E6F6EC`; In Review `#1D4ED8` on `#E8F0FE`; In Progress `#B45309` on `#FEF3DE`; Documents Pending `#C2410C` on `#FEEDE0`.
- Actions: eye + pencil `26px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 38 entries` 13px `#6B7A94`; pagination squares `32px`, active `#0B2A5B` white, `« ‹ 1 2 3 4 5 › »`.

### Right rail
**IHT Summary (This Year)** — title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Donut left ~`128px`, thickness `28px`, gapped arcs: `#E2564F` (52.3%), `#22C55E` (15.5%), `#F59E0B` (10.6%), `#8B5CF6` (21.6%); center `£1,056,480` 16px/700 `#0F2544` + `Est. Tax Liability` 11px `#6B7A94`.
- Legend right: dot `9px` + label 13px `#33455F`, amount 13px `#4A5B75`, percent `#6B7A94` — Est. Tax Liability £1,056,480 (52.3%), Tax Saved £312,750 (15.5%), Planning in Progress £214,600 (10.6%), No Liability / Nil Tax £435,320 (21.6%).
- Divider `1px #F2F5FA`, centred link `View full summary →` 13px `#2E5BFF`.

**Quick Actions** — title 16px/600; rows `24px`, doc icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Start New IHT Enquiry, Upload Estate Details, IHT Planning Checklist, Nil Rate Band Calculator, Gift & Exemption Guide, View All Clients.

**Upcoming IHT Deadlines** — title + `View Calendar` link. Rows: date chip `36px` square radius `8px` tinted (day 15px/700 + `AUG`/`SEP`/`OCT` 10px) accent red `#E2564F` / amber `#D98324` / green `#16A34A` / violet `#7C5CE0`; title `IHT Return – Estate of John Miller` 13.5px/600 `#1B3358`, `Due Date: 30 Aug 2025` 12.5px `#7A8AA3`; right `In 22 days` 12.5px/600 accent — John Miller; Sarah Patel; Lisa Roberts; Amit Kumar. Footer `+ View all deadlines →` 13px `#2E5BFF`.

## Bottom row (3 cards)
### Est. Tax Liability Trend (This Year)
- Title 16px/600 + `(This Year)` 13px `#6B7A94`.
- Single area line **violet** `#8B5CF6`, 2.5px stroke, circular white-fill markers with violet ring, gradient fill `rgba(139,92,246,.12) → transparent`.
- Y axis `£0–£1.2M` steps `£300K` (`£300K`, `£600K`, `£900K`, `£1.2M`), 11.5px `#8494AC`; X axis Apr→Mar; gridlines `#EEF2F8`.
- Below: two bordered stat boxes, radius `10px`, border `1px #EDF1F7`, padding `12px` — `Est. Tax Liability (This Year)` 12px `#6B7A94` / `£1,056,480` 17px/700 `#0F2544`; second box `+12.6%` 16px/700 `#16A34A` / `vs last year` 11.5px `#6B7A94`.

### Estate Value Distribution (This Year)
- Donut left ~`125px`, thickness `28px`, gapped arcs: `#2E5BFF`, `#22C55E`, `#F59E0B`, `#8B5CF6`.
- Legend right: dot `9px` + label 13px `#33455F`, count + percent 13px `#4A5B75` — £0 - £1M 10 (26.3%), £1M - £2M 12 (31.6%), £2M - £3M 8 (21.1%), £3M+ 8 (21.1%).
- Divider then centred block: `Average Estate Value` 12.5px `#6B7A94` + `£2,124,000` 17px/700 `#0F2544`.

### Planning Type (This Year)
- Rows: label 13px `#33455F` left (fixed ~`95px`), horizontal bar centre — fill `#2E5BFF`, height `8px`, radius `4px`, width proportional; right `16 (42.1%)` 12.5px `#4A5B75`.
- IHT Planning 16 (42.1%), IHT Return 15 (39.5%), Review / Update 5 (13.2%), Deeds & Trusts 2 (5.3%).
- Divider then `Total Cases` 13.5px `#33455F` + `38` 15px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`/`#22C55E`, amber `#F59E0B`, teal `#14B8A6`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 3; bottom row 2 + 1; table hides `Nil Rate Band Available`.
- `<1280px`: right rail drops below table; table also hides `UTR` column.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards (client, estate value, liability, status); bottom cards single column; trend stat boxes stack.
