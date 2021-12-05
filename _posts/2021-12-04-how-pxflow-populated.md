---
layout: post
title:  "How is the .pxFlow page populated"
id: MTYDKUVMPA
---

<img src="https://i.postimg.cc/25x2ZRkD/MTYDKUVMPA-Flow-Reference.png" alt="">

<p>The following piece of Java is in the <i>Task_AssignmentSF2_circum0</i> method of the flow.</p>

<img src="https://i.postimg.cc/VkqFxQbC/MTYDKUVMPA-Java-Entry-Point.png" alt="">

<pre>AddTaskToFlowPath("AssignmentSF2", "ASSIGNMENT", "Step B", true, "", false)</pre>

<p>This method will update the <i>pyWorkPage.pxFlow(Current_Flow).pyFlowPath</i> page list.</p>

<p>The first part of the method is intended to check whether the <i>FlowPath</i> page already exists in the page list.</p>



<p>If the task is found, then the <i>foundTaskInHistory</i> will be <i>true</i>.</p>

<img src="https://i.postimg.cc/76s9K3Jt/MTYDKUVMPA-Java-Add-To-Flow-Path1.png" alt="">

<img src="https://i.postimg.cc/BbRg0Jgx/MTYDKUVMPA-Clipboard-Found.png" alt="">

<p>The next part of the method is intended to remove the subsequent <i>FlowPath</i> pages.</p>



<p><b>If the task is found</b>, the subsequent pages in the <i>.pyFlowPath</i> page list will be removed and the algorithm ends.</p>

<img src="https://i.postimg.cc/q7r1qMdw/MTYDKUVMPA-Java-Add-To-Flow-Path2.png" alt="">

<img src="https://i.postimg.cc/h4YMx0Bf/MTYDKUVMPA-Clipboard-Found-Remove.png" alt="">

<p><b>If the task is not found</b>, we will look at the <i>pyFlowData</i>. As we have seen (....), the <i>pyFlowData</i> page will store the information of the navigation links in a Screen Flow.</p>

<p>We search for the <i>taskName</i> in the <i>pyFlowData</i> page; if we found it, we'll loop through the subsequent <i>.pySteps</i> pages. In every iteration, we'll remove every <i>pyFlowPath</i> that matches the current <i>pySteps</i> page.</p>

<img src="https://i.postimg.cc/4yY5qYzf/MTYDKUVMPA-Java-Add-To-Flow-Path3.png" alt="">

<img src="https://i.postimg.cc/yYSjTS1n/MTYDKUVMPA-Clipboard-Flow-Data.png" alt="">

<p>Finally (if the task has not been found), a <i>newTaskPage</i> is created and appended to the <i>taskPath</i> (i.e. append to the <i>pyFlowPath</i> page list).</p>

<img src="https://i.postimg.cc/1t5M0CXN/MTYDKUVMPA-Java-Add-To-Flow-Path4.png" alt="">

<p>There is an additional line to add the step to the stage list but we will study this function in another post.</p>

<img src="https://i.postimg.cc/qvVLDMgr/MTYDKUVMPA-Java-Add-To-Flow-Path5.png" alt="">
