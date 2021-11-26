---
layout: post
title:  "Customize the Signature control buttons"
id: JQIYEAHVZV
---
<h2>Customize the Signature control buttons</h2>

<p>Let's suppose that our Signature control is configured as shown in the following image. The Signature name is <i>MySignature</i> and the Attachment category is <i>File</i>, we'll need these two configurations to identify uniquely this signature control.</p>

<img src="https://i.postimg.cc/jjB4v5gv/JQIYEAHVZV-Settings.png" alt="">

<p>We can customize the Signature's <i>Accept</i> and <i>Clear</i> buttons using the following scripts respectively.</p>

<pre>pega.ui.signature.acceptSignature( strSignatureName, strSignatureCategory )
pega.ui.signature.clearSignature( strSignatureName, strSignatureCategory )</pre>

<p>The parameters are the Signature's name and category as shown in the first image.</p>

<div class="tip">
  <p>Once we customize the <i>Accept</i> and <i>Clear</i> actions, it is better to hide the default accept/clear actions.</p>

  <img src="https://i.postimg.cc/ZnSFtQhd/JQIYEAHVZV-Hide-Actions.png" alt="">
</div>

<h3>Example</h3>

<p>Let's customize two buttons to accept and clear the signature control presented in the first image. The following image shows the configuration of the Accept button.</p>

<img src="https://i.postimg.cc/RVvQg2dr/JQIYEAHVZV-Script.png" alt="">

<p>As a result, the <i>Accept</i> and <i>Clear</i> buttons will work as expected.</p>

<img data-gifffer="https://i.postimg.cc/65zVgzb8/JQIYEAHVZV-Result.gif" />

<h3>Reference</h3>

<p>1. <a href="https://docs.pega.com/user-experience/84/customizing-signature-capture-control">Customizing a Signature capture control | Pega</a></p>
