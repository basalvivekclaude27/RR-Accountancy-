# Login Screen — Look & Feel

## Layout
- Full-height page, centered column, max-width `1100px`, vertical padding `48px`.
- Header block (title + subtitle) → segmented toggle → two stacked cards.
- Cards stack vertically on desktop; both visible. Toggle scrolls/activates the matching card.
- Card gap `28px`. Page background `#F4F6FA`.

## Header
- Title: `Welcome to R & R Accountancy Services` — 28px, 700, `#0B2A5B`, centered.
- Subtitle: `Please choose how you want to continue` — 15px, 400, `#5A6B85`.

## Toggle
- Single pill, height `56px`, radius `999px`, overflow hidden, two equal halves.
- Left (Admin): bg `#0B2A5B`, white text, user icon.
- Right (Client): bg `#0E7C5A`, white text, user icon.
- Inactive half: `#E8EDF5` bg, `#5A6B85` text.
- Label 16px / 600.

## Cards (shared structure)
- Radius `20px`, shadow `0 8px 28px rgba(11,42,91,.10)`.
- Two columns: illustration panel (left, ~40%) + form panel (right, ~60%, white, radius `16px`, inset margin `16px`).
- Illustration panel: flat vector art, centered; trust strip pinned bottom-left with shield icon, 13px white text.

### Admin card
- Panel gradient `#1D4ED8 → #2E5BFF` (135deg).
- Heading `Admin Login` 26px/700 `#0B2A5B`; sub `Secure access for administrators only` 14px `#6B7A94`.
- Trust strip: `Secure • Private • Protected`.
- Button: bg `#1D4ED8`, white, 16px/600, height `52px`, radius `10px`, right arrow icon.

### Client card
- Panel gradient `#5FC3A3 → #34A98A` (135deg).
- Heading `Client Login` 26px/700 `#0E7C5A`; sub `Access your account and manage your records`.
- Trust strip: `Your data is safe with us`.
- Button: bg `#0E7C5A`, right arrow icon.
- Footer: `New Client?` `#5A6B85` + `Register Here` link `#0E7C5A`, 600.

## Fields
- Label 13px/600 `#243B5E`, margin-bottom `6px`.
- Input height `48px`, radius `10px`, border `1px solid #DCE3ED`, bg `#F8FAFD`, padding-left `44px`.
- Leading icon `#9AA7BC` (envelope / lock), 18px, absolute left `14px`.
- Password: trailing eye toggle `#9AA7BC`, right `14px`.
- Focus: border + 3px ring in card accent colour.
- Placeholder `#9AA7BC`.

## Row below password
- Flex, space-between, margin `14px 0 20px`.
- Checkbox `18px`, radius `4px`, checked fill = card accent, white tick. Label `Remember Me` 14px `#3C4C66`.
- `Forgot Password?` link 13px, card accent, no underline.

## Type
- Family: Inter / system sans-serif.
- Weights used: 400, 600, 700.

## Responsive
- `< 900px`: illustration panel hidden, form full width; toggle stays full width; card padding `20px`.
