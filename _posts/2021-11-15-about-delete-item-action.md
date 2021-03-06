---
layout: post
title:  "About the \"Delete item\" action"
id: VV3SKOR3Z2
---

<h2 id="about-the-delete-item-action">About the “Delete item” action</h2>

<p>This action is pretty simple and straightforward: It just removes an item from a Table or a Repeating Dynamic Layout.</p>

<p>First of all, we can find this action in the List category as shown in the next image.</p>

<img src="https://i.postimg.cc/28R0CL0D/JFPKZLLRCQ-Action-Set.png" alt="image">

<p>We can use this action inside a row in a Table or a section in a Repeating Dynamic Layout.</p>

<div class="important">
  <p>
    The <i>Delete item</i> action <b>won't work</b> in a Table which has the option <em>None</em> selected in the <em>Row editing</em> options.
  </p>
    <img src="https://i.postimg.cc/hjX2tLFS/JFPKZLLRCQ-Table-Edit.png">
</div>

<p>The following animation shows the effect of the <em>Delete item</em> action (applied to the 🗑 icon) in a Table.</p>

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/QIaGe12ltC.gif" />

<p>The following animation shows the effect of the <em>Delete item</em> action (applied to the “Remove” button) in a Repeating Dynamic Layout.</p>

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/xt4ddcBPwT.gif" />

<p>The good thing about this action is that it actually removes the page
from the Page List, so it’s not just a visual effect. The following 
animation shows how the page <em>CountryList.pxResults(2)</em> is removed from both the Table and the Page List.</p>

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/8CL0nGgwDN.gif" />

<div class="block important">
    The <em>Delete item</em> action <strong>won't work</strong> if the Data Source is a Data Page in <em>Read only</em> mode.
    <img src="https://i.postimg.cc/Y9TbGvkQ/JFPKZLLRCQ-Data-Page.png">
</div>

<h3 id="display-confirmation-message">Display confirmation message</h3>

<p>Sometimes we need to display a popup with a confirmation message in 
order to avoid an accidental deletion. For this purpose we will check 
the <em>Display confirmation message</em> option in the configuration of the <em>Delete item</em> action (it’s the only configuration).</p>

<p><img src="https://i.postimg.cc/x8HgK9Th/JFPKZLLRCQ-Confirmation.png" alt="image"></p>

<div class="block important">
    The <em>Display confirmation message</em> option <strong>won't work</strong> if the Table is not optimized (template table), so we need to make sure that the <em>Optimize code</em> is selected (checking the <em>Allow</em> option) in the Table configuration.
    <img src="https://i.postimg.cc/G3sM0xJK/JFPKZLLRCQ-Optimized-Table.png">
</div>

<p>The following animation shows the effect of the <em>Display confirmation message</em> option.</p>

<img data-gifffer="https://raw.githubusercontent.com/dfrankmv/pegablog/gh-pages/img/JOQJwuhEsr.gif" />
