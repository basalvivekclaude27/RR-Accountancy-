# Accounts Preparation Screen — Look & Feel

Same shell and layout system as `bookkeeping.md` (sidebar `272px` navy `#0B2A5B`, gold active item, trust card, navy footer strip, white topbar with hamburger + breadcrumb + bell/avatar). Active sidebar item = `Accounts Preparation`.

## Topbar
- Title `Accounts Preparation` 20px/700 `#0B2A5B`.
- Breadcrumb: `Dashboard` `#2E5BFF` › `Accounts Preparation` `#6B7A94`, 13px.

## Page header
- Title `Accounts Preparation` 24px/700 `#0F2544`; sub `Accurate accounts, complete peace of mind. We prepare reliable financial statements you can trust.` 14px `#6B7A94`.
- Button right: `+ New Accounts Preparation Job` — bg `#0B2A5B`, white 14px/600, height `44px`, radius `8px`, padding `0 20px`.

## KPI strip (5 cards)
Grid 5 × equal, gap `16px`, white, radius `12px`, border `1px #EDF1F7`, padding `16px`. Icon tile `46px` radius `10px` tinted; label 13.5px `#6B7A94`; value 26px/700 `#0F2544`; footnote 12.5px.

| Card | Value | Footnote | Accent / tile |
|---|---|---|---|
| Total Clients | 128 | +12 this month | `#3B82F6` / `#EAF2FE` |
| Active Jobs | 42 | +8 this month | `#16A34A` / `#E8F6EC` |
| Completed Jobs | 67 | +15 this month | `#F59E0B` / `#FEF2E3` |
| Pending Deliverables | 23 | `View pending` link | `#8B5CF6` / `#F1ECFE` |
| This Month Revenue | £18,950 | +20.4% vs last month | `#2E5BFF` / `#EAF2FE` |

## Main grid
Three columns: filters `~180px` · table (fluid) · right rail `~320px`. Gap `20px`.

### Filters panel
- White card, radius `12px`, padding `20px`; funnel icon `#2E5BFF` + `Filters` 16px/600.
- Fields (label 13px/600 `#33455F`; control height `42px`, radius `8px`, border `1px #E2E8F2`, 13.5px, chevron/calendar `#9AA7BC`): Client (`All Clients`), Job Status (`All Status`), Financial Year (`Select Year`, calendar icon), Account Type (`All Types`), Assigned To (`All Staff`).
- `Apply Filters` full width `44px` bg `#0B2A5B` white; `Reset` full width `44px` white bg, border `1px #E2E8F2`, text `#33455F`.

### Jobs table card
- Header: `Accounts Preparation Jobs` 17px/600 + search `260px` × `40px`, radius `8px`, border `1px #E2E8F2`, placeholder `Search client or job...`, magnifier `#9AA7BC`.
- Head row bg `#FAFBFD`, 12.5px/600 `#5A6B85`: `Client / Business · Financial Year · Account Type · Status · Last Updated · Actions`.
- Rows height `55px`, divider `1px #F2F5FA`, hover `#FAFCFF`.
- Client cell: `34px` initials circle on rotating pastel bg (`#FBE9EC`, `#FDF1DC`, `#E5F5EC`, `#F0EAFB`, `#E8F0FE`), name 14px/600 `#1B3358`, ref `ACC-1006` 12.5px `#7A8AA3`.
- Financial Year `2024 - 2025` / Account Type (`Statutory Accounts`, `Management Accounts`) / Last Updated: 13.5px `#4A5B75`.
- Status pill radius `999px`, 12px/600, padding `4px 12px` — In Progress `#1D4ED8` on `#E8F0FE`; Pending Review `#B45309` on `#FEF3DE`; Draft `#6D28D9` on `#F1EAFD`; Completed `#15803D` on `#E6F6EC`.
- Actions: eye + pencil buttons `28px` radius `6px` bg `#F3F6FB` icon `#5A6B85`, then vertical dots `#9AA7BC`.
- Footer: `Showing 1 to 6 of 42 entries` 13px `#6B7A94`; pagination squares `34px` radius `6px` border `#E2E8F2`, active `#0B2A5B` white, controls `« ‹ 1 2 3 4 5 › »`.

### Right rail
**Accounts Overview** — header 16px/600 + `View All` `#2E5BFF` 13px. Left: pie chart (~`110px`, solid wedges, thin white separators) `#22C55E` / `#2E5BFF` / `#F59E0B` / small `#8B5CF6` sliver. Right: legend rows — dot `9px` + label 13.5px `#33455F`, right value 13.5px `#4A5B75`: Completed 67 (55%), In Progress 42 (34%), Pending 23 (19%). Divider `1px #EDF1F7`, then `Total` 13.5px `#6B7A94` + `132` 14px/700 `#0F2544`.

**Quick Actions** — title 16px/600; rows `31px`, icon `#2E5BFF` + label 13.5px `#33455F`, chevron `#B4BECD`, hover `#F7FAFF`: Upload Documents, Request Information, Prepare Financial Statements, Share with Client (share icon), Manage Templates, Chart of Accounts.

**Reminders** — title + `View All`. Rows: icon tile `34px` radius `8px` tinted blue / amber / violet; title 13.5px/600 `#1B3358`, client 12.5px `#7A8AA3`; right date 12.5px/600 accent (`#E2564F`, `#D98324`, `#7C5CE0`) — Accounts Review Due · 10 Aug 2025; Financial Statements Due · 22 Aug 2025; Management Accounts Due · 31 Aug 2025. Footer `+ See all reminders` 13px `#2E5BFF`.

## Bottom row (2 cards)
### Monthly Summary (This Month)
- 4 segments, vertical dividers `1px #EDF1F7`; icon tile `34px` radius `8px` tinted; label 12.5px `#6B7A94`; value 24px/700 `#0F2544`; delta 12px `#16A34A`.
- Jobs Added `14` (+27%), Jobs Completed `11` (+22%), Reports Delivered `9` (+29%), Revenue Generated `£18,950` (+20.4%).

### Accounts Preparation Progress
- Donut left ~`140px`, thickness `30px`, gapped arcs: `#22C55E`, `#2E5BFF`, `#8B5CF6`, `#F59E0B`.
- Legend right: dot `9px` + label 13.5px `#33455F`, right `55% (67)` style 13.5px `#4A5B75` — Completed 55% (67), In Progress 34% (42), Draft 8% (10), Pending 3% (3).
- Divider then `Total` `#6B7A94` + `122` 14px/700 `#0F2544`.

## Palette
Inherits: navy `#0B2A5B`, navy-deep `#0F2544`, gold `#C98A2E`, blue `#2E5BFF`, green `#16A34A`, amber `#F59E0B`, violet `#8B5CF6`, red `#E2564F`, muted `#6B7A94`, border `#EDF1F7`, page-bg `#F7F9FC`.

## Responsive
- `<1440px`: KPI strip wraps 3 + 2.
- `<1280px`: right rail drops below table.
- `<1024px`: sidebar → `72px` icon rail; filters become a drawer.
- `<768px`: table → stacked cards; bottom row single column.
