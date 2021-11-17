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
    In order for the action to work, it is necessary to make the row Inline editable.
    <img src="https://user-images.githubusercontent.com/19811297/141969058-552c5bbc-0e44-45a3-9198-558b003fcf1a.png" />
</div>

The following animation shows the effect of the _Delete item_ action (applied to the 🗑 icon) in a Table. 

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/SMYeIXpBrj.gif" />

The following animation shows the effect of the _Delete item_ action (applied to the "Remove" button) in a Repeating Dynamic Layout.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/xt4ddcBPwT.gif" />

The good thing about this action is that it actually removes the page from the pagelist, so it's not just a visual effect. The following animation shows how the page _CountryList.pxResults(2)_ is removed from both the Pagelist and the Table.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/zLzhMNY5EU.gif" />

<div class="block important">
    If the data source is a <strong>Data Page</strong> in <strong>read only</strong> mode, then the <em>Delete item</em> action won't be able to remove the page from the Data Page list.
    
    <img src="https://user-images.githubusercontent.com/19811297/141976362-6fbb50c6-2f7a-418f-982f-4c47ad4ce06b.png" />
</div>

### Display confirmation message

Let's focus on the option _Display confirmation message_.

## Notes
1. This action works when used in:
    - A grid table
    - A repeating dynamic layout


