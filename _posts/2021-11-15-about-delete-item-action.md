---
layout: post
title:  "About the \"Delete item\" action"
---

## About the "Delete item" action

This action is pretty simple and straightforward: It just removes an item from a table or repeating dynamic layout. 

First of all, we can find this action in the List category as shown in the next image.

![image](https://user-images.githubusercontent.com/19811297/141845370-be3a5538-aada-429b-8b5d-c5be8579be58.png)

We can use this action inside a row in a _Table_ or a section in a _Repeating Dynamic Layout_. 

In the following gif we have a Table and we've added the _Delete item action_ in the 🗑 icon. 

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/SMYeIXpBrj.gif" />

In the following gif we have a _Repeating Dynamic Layout_ and we've added the _Delete item action_ in the "Remove" button.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/qCSBer1VzN.gif" />

<div class="block warning">
    In order for the action to work, it is necessary to make the row Inline editable.
    <img src="https://user-images.githubusercontent.com/19811297/141969058-552c5bbc-0e44-45a3-9198-558b003fcf1a.png" />
</div>

The good thing about this action is that it actually removes the page from the pagelist, so it's not just a visual effect.

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/EiTcQ3gti6.gif" />

<div class="block important">
    If the data source is a <em>Data Page</em> in <b>read only</b> mode, then the <em>Delete item</em> action will not be able to remove the page from the <em>.pxResult</em> pagelist.
    <img src="https://user-images.githubusercontent.com/19811297/141976362-6fbb50c6-2f7a-418f-982f-4c47ad4ce06b.png" />
</div>

### Display confirmation message



## Notes
1. This action works when used in:
    - A grid table
    - A repeating dynamic layout


