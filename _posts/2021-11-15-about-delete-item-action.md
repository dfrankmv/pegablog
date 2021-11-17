---
layout: post
title:  "About the \"Delete item\" action"
---

## About the "Delete item" action

This action is pretty simple and straightforward: It just removes an item from a Table or a Repeating Dynamic Layout. 

First of all, we can find this action in the List category as shown in the next image.

![image](https://user-images.githubusercontent.com/19811297/141845370-be3a5538-aada-429b-8b5d-c5be8579be58.png)

We can use this action inside a row in a Table or a section in a Repeating Dynamic Layout.

<div class="block warning">
    The <em>Delete item</em> action <strong>won't work</strong> in a Table which has the option <em>None</em> selected in the <em>Row editing</em> options.
    <img src="https://user-images.githubusercontent.com/19811297/142218077-c45b0b17-6cb1-44df-835e-6ccc84910792.png" />
</div>

The following animation shows the effect of the _Delete item_ action (applied to the 🗑 icon) in a Table. 

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/SMYeIXpBrj.gif" />

The following animation shows the effect of the _Delete item_ action (applied to the "Remove" button) in a Repeating Dynamic Layout.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/xt4ddcBPwT.gif" />

The good thing about this action is that it actually removes the page from the Page List, so it's not just a visual effect. The following animation shows how the page _CountryList.pxResults(2)_ is removed from both the Table and the Page List.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/zLzhMNY5EU.gif" />

<div class="block important">
    The <em>Delete item</em> action <strong>won't work</strong> if the Data Source is a Page List in <em>Read only</em> mode.
    <img src="https://user-images.githubusercontent.com/19811297/141976362-6fbb50c6-2f7a-418f-982f-4c47ad4ce06b.png" />
</div>

### Display confirmation message

Sometimes we need to display a popup with a confirmation message in order to avoid an accidental deletion. For this purpose we will check the option _Display confirmation message_.

<div class="block important">
    The <em>Display confirmation message</em> <strong>won't work</strong> if the table is not optimized (template table), so we need to make sure that the <em>Optimize code</em> is selected (checking the <em>Allow</em> option).
    <img src="https://user-images.githubusercontent.com/19811297/142223013-7e6d801e-3dd6-46ed-9949-1fb538fd8328.png" />
</div>

### pzRemoveFromRepeatSource
wip

## Notes
1. This action works when used in:
    - A grid table
    - A repeating dynamic layout


