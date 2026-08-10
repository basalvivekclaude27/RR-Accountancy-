# Dashboard Screen — Look & Feel

## Shell
- Two-column app shell: fixed sidebar `272px` + fluid content area.
- Content bg `#F7F9FC`, padding `24px 28px`, scrolls independently.
- Bottom footer strip: navy `#0B2A5B`, height `48px`, centered gold italic serif text `Reliable. Professional. Personal. Your Financial Success is Our Priority.` 15px `#F0C674`, flanked by thin gold diamond dividers.

## Sidebar
- Bg `#0B2A5B`, full height, no scrollbar chrome.
- Logo block top, padding `24px 20px`: white serif `R & R` wordmark + gold arrow/bar mark, `Accountancy Services` beneath, then gold tagline `Your Success is Our Business` 11px with arrow dashes.
- Nav items: height `41px`, padding-left `20px`, icon `20px` + label 14.5px `#C9D5E8`.
- Active item: gold gradient `#C98A2E → #B8791F`, white text/icon, small chevron on right edge.
- Hover: bg `rgba(255,255,255,.06)`.
- Bottom trust card: margin `16px`, padding `14px`, radius `10px`, bg `#0E2F63`, border `1px solid #C98A2E`, shield icon gold; 3 lines 13px — white label + gold keyword (`Trusted Advice.` / `Expert Support.` / `Every Step of the Way.`).

## Topbar
- Left: `Welcome back,` 14px `#6B7A94`; brand line `R & R Accountancy Services` 26px/700 `#0B2A5B`; sub `Here's your business overview for today.` 14px `#6B7A94`.
- Right: bell icon `#334E7B` with red badge (`#E5484D`, 18px circle, white 11px count); vertical divider; avatar 42px circle `#E3E9F3` with user glyph; name 15px/600 `#0B2A5B` + role 12.5px `#6B7A94`; chevron.
- Below right: date pill — white bg, border `1px solid #E2E8F2`, radius `8px`, height `40px`, calendar icon `#0B2A5B` + `09 August 2025` 14px/600.

## KPI row (4 cards)
- Grid 4 × equal, gap `20px`, card white, radius `14px`, border `1px solid #EDF1F7`, padding `18px 20px`, shadow `0 1px 3px rgba(16,32,64,.05)`.
- Icon tile `44px`, radius `12px`, tinted bg + matching glyph.
- Label 14px `#6B7A94`; value 28px/700 `#0F2544`; delta 12.5px in accent.
- Sparkline full card width, height `56px`, 2px stroke + soft gradient fill.
- Accents: Clients `#3B82F6` / tile `#EAF2FE`; Revenue `#16A34A` / `#E8F6EC`; Receivables `#F59E0B` / `#FEF2E3`; Tax `#8B5CF6` / `#F1ECFE`.

## Charts row (2 cards, ~60/40)
### Revenue Overview
- Title 16px/600 `#0F2544`; right: `This Year` select — height `34px`, radius `8px`, border `#E2E8F2`, 13px.
- Area line chart: stroke `#2E5BFF` 2.5px, circular markers (white fill, blue ring), fill gradient `rgba(46,91,255,.18) → transparent`.
- Y axis `£0–£60K` steps of `£15K`, labels 11.5px `#8494AC`; X axis Jan–Aug; horizontal gridlines `#EEF2F8` only.

### Income vs Expenses
- Donut, thickness ~`34px`, gap between arcs; income `#4CAF7D`, expenses `#E2564F`.
- Center: `Net Profit` 12.5px `#6B7A94` + `£18,750` 22px/700 `#0F2544`.
- Legend right: colour dot `10px` + label 14px, amount 14px/600, percentage right-aligned `#6B7A94`.

## Mid row (3 cards)
### Recent Activities
- List rows separated by `1px #F0F3F8`, padding `12px 0`.
- Round status icon `28px` (green tick, amber clock, violet doc, blue doc) on tinted circle.
- Title 14px/600 `#1B3358`, meta 12.5px `#7A8AA3`, right timestamp 12.5px in row accent colour.

### Top Services by Usage
- Header with `View All` link 13px `#2E5BFF`.
- Rows: label 14px `#33455F` left, track `#EDF1F7` height `7px` radius `4px`, fill `#2E5BFF`, right percent 13.5px/600.

### Upcoming Deadlines
- Header with `View Calendar` link.
- Rows: date chip `48px` square, radius `10px`, tinted bg, day 17px/700 + month 10.5px uppercase in accent (red `#E2564F`, amber `#D98324`, violet `#7C5CE0`).
- Title 14px/600, sub 12.5px `#7A8AA3`; right pill (`10 days left`) radius `999px`, 11.5px, tinted bg/text matching accent.

## Bottom stats strip
- Single white card, radius `14px`, 4 equal segments split by `1px #EDF1F7` vertical dividers, padding `20px 24px`.
- Illustrative icon `44px` navy line-art left of each block.
- Label 14px `#6B7A94`, value 26px/700 `#0F2544`, footnote 12.5px in accent (`+4 this month` green, `Next: 12 Aug 2025` blue, `Excellent` green).

## Type
- UI: Inter / system sans-serif; weights 400/600/700.
- Logo + footer tagline: serif / script accent in gold `#F0C674`.

## Palette
| Token | Hex |
|---|---|
| navy | `#0B2A5B` |
| navy-deep | `#0F2544` |
| gold | `#C98A2E` |
| gold-light | `#F0C674` |
| blue | `#2E5BFF` |
| green | `#16A34A` |
| red | `#E2564F` |
| amber | `#F59E0B` |
| violet | `#8B5CF6` |
| text-muted | `#6B7A94` |
| border | `#EDF1F7` |
| page-bg | `#F7F9FC` |

## Responsive
- `<1280px`: KPI grid 2×2, charts stack.
- `<1024px`: sidebar collapses to `72px` icon rail (logo mark only, labels hidden, trust card hidden).
- `<768px`: all cards single column; topbar right block wraps under title.
