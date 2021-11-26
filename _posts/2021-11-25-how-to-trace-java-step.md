---
layout: post
title:  "How to trace a java step"
id: YPZFUDIVRK
---

<h2>How to trace a java step</h2>

<p>Just add the following function in the java step you want to trace.</p>

<pre>pega_rules_utilities.sendDebugMessageToTracer( strMessage , strRulesetName );</pre>

<p>The <i>strMessage</i> parameter is the string that will appear in the tracer.</p>

<p>The <i>strRulesetName</i> parameter is the name of a traced ruleset. It can be <i>"Pega-ProCom"</i> which is a common ruleset.</p>

<div class="block important">
  In order to see the message in the tracer we need to add the <i>Debug</i> event to the list of Event types to trace.

  <img src="https://i.postimg.cc/nhcFwrb4/YPZFUDIVRK-Settings.png" alt="">

  </div>

<div class="block important">
  <p>The message <b>will not be displayed</b> if the ruleset <i>strRulesetName</i> is not selected in the Tracer settings.</p>

  <img src="https://i.postimg.cc/VLwLn4KH/YPZFUDIVRK-Rulesets.png" alt="">
</div>

<h3>Example</h3>

<p>In the following image we have added the function to display a message in the tracer.</p>

<img src="https://i.postimg.cc/zDKGy4Fg/YPZFUDIVRK-Java.png" alt="">

<p>When we run this activity, we can see the message in the tracer, as shown in the following image.</p>

<img src="https://i.postimg.cc/pXBVZ87V/YPZFUDIVRK-Message.png" alt="">
