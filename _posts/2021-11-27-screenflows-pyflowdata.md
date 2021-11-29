---
layout: post
title:  "Screenflows - pyFlowData"
id: XWURNONRNL
---

<h2>Screenflows &mdash; pyFlowData</h2>

<p>The <i>pyFlowData</i> page is used to show the navigation links in a Screen Flow.</p>

<p>The following image shows the navigation links (using the <i>TreeNavigation7</i> harness). Note that the main Screen Flow has a sub-flow called <i>MySubFlow</i> with two steps.</p>

<img src="https://i.postimg.cc/B6BD8bMH/XWURNONRNL-Screen-Flow.png" alt="">

<p>The following image shows their corresponding <i>pyFlowData</i> page in the clipboard.</p>

<img src="https://i.postimg.cc/Qdh25y87/XWURNONRNL-Clipboard.png" alt="">

<h3>The Valid path</h3>

<p>Before looking at the details of the algorithm, let's see an example of how it works.</p>

<p>Let's use the following Screenflow as an example. The decision shape evaluates <i>.pySelected == true</i>.</p>

<img src="https://i.postimg.cc/wT88xBn0/XWURNONRNL-Example-Flow.png" alt="">

<p>The following animation shows the behaviour of the navigation links in runtime. Is the shown behavior as you expected?</p>

<img data-gifffer="https://i.postimg.cc/PqF9gfmb/XWURNONRNL-Example-SF.gif" />

<p>Let's focus on the <i>Step A</i>. In this step the navigation shows the links for steps <i>A</i>, <i>B</i> and <i>D</i>.</p>

<p>But when we are in the <i>Step B</i>, the navigation shows the links for steps <i>A</i>, <i>B</i>, <i>C</i> and <i>MySubFlow</i>. <b>Why does this happen?</b></p>

<p>In order to understand the algorithm, we will introduce the concept of <b>Valid path</b>. The valid path is the expected list of steps given the current data. The navigation links will always show the <i>Valid path</i>.</p>

<p>At the beginning of the case the <i>.pySelected</i> property is <i>false</i>, so the decision shape will take the <i>Not true</i> path. The following image shows the valid path (in blue) at the beginning of the case.</p>

<img src="https://i.postimg.cc/G3DVfC9Y/XWURNONRNL-Flow-Step-A.png" alt="">

<p>Now, in the <i>Step A</i> we check the <i>pySelected</i> checkbox and proceed to the next step, so the <i>.pySelected</i> property is <i>true</i> and the decision shape will take the <i>True</i> path. The following image shows the valid path (in blue) when we proceed to the <i>Step B</i>.</p>

<img src="https://i.postimg.cc/dV5xCQSM/XWURNONRNL-Flow-Step-B.png" alt="">

<p>Now we proceed to the next step without changes. The following image shows the valid path (in blue) when we proceed to the <i>Step C</i>.</p>

<img src="https://i.postimg.cc/DyKHkMbN/XWURNONRNL-Flow-Step-C.png" alt="">

<p>Finally, in the <i>Step C</i> we uncheck the <i>pySelected</i> checkbox and proceed to the next step, so the <i>pySelected</i> property is <i>false</i> and the decision shape will take the <i>Not true</i> path. The following image shows the valid path (in blue) when we proceed to the <i>Step C</i>.</p>

<img src="https://i.postimg.cc/8PB31mRz/XWURNONRNL-Flow-Step-Sub-Flow.png" alt="">

<p>Now that we know how the <i>Valid path</i> works, let's do an experiment. Let's make a small modification to the flow and connect the step D to the step A, what would you expect the navigation links show?</p>

<img src="https://i.postimg.cc/cHPqn1C1/XWURNONRNL-Flow-Experiment.png" alt="">

<p>The following image shows the result in runtime at the beginning of the case. Note that the <i>Step A</i> appears twice.</p>

<img src="https://i.postimg.cc/FR43s3gh/XWURNONRNL-Step-AExperiment.png" alt="">

<p>The valid path of this modification at the beginning of the case is shown in the following image. The <i>Step A</i> is being evaluated twice in the valid path as there are two connectors pointing to that step.</p>

<img src="https://i.postimg.cc/yYmqDHHB/XWURNONRNL-Flow-Experiment-Valid-Path.png" alt="">

<p>To finish our example, let's make another small modification to our flow. Let's change the condition in the decision shape to <i>.pySelected != true</i>.</p>

<p>The following image shows the <i>Valid path</i> at the beginning of the case.</p>

<img src="https://i.postimg.cc/Pxgcq6VP/XWURNONRNL-Flow-Experiment2-Valid-Path.png" alt="">

<p>Note that the <i>Step A</i> is part of the <i>Valid path</i> and the <i>Invalid path</i> at the same time! We can verify this fact looking into the <i>pyFlowData</i> page in the clipboard.</p>

<p>The following image shows the <i>pyFlowData.pySteps(1)</i> page which corresponds to the <i>Step A</i> in the <i>Valid path</i>. Note that the <i>.pyDisplay</i> property is set to <i>true</i> which indicates that this step will be shown in the navigation links.</p>

<img src="https://i.postimg.cc/T3BnFZKL/XWURNONRNL-py-Steps1.png" alt="">

<p>The following image shows the <i>pyFlowData.pySteps(6)</i> page which corresponds to the <i>Step A</i> in the <i>Invalid path</i>. Note that the <i>.pyDisplay</i> property is set to <i>false</i> which indicates that this step won't be shown in the navigation links.</p>

<img src="https://i.postimg.cc/k58WmfSj/XWURNONRNL-py-Steps6.png" alt="">

<h3>The code behind the algorithm</h3>

<p>Using the tracer we can get the step where the <i>pyFlowData</i> is populated. The activity responsible for this is <i>Work-.GetFlowData</i> which uses the following function in a Java step.</p>

```java
pega_processengine_flowutilities.getNavigationPath(interestPage, strFlowClass, strFlowType, pg_pxFlow.getString(".pyParentFlowPath"), "pyFlowData", tools.getParamAsBoolean(PropertyInfo.TYPE_TRUEFALSE, "deferredLoad"));
```

<p>As an example, using the main flow <i>OOO-PGB-Work-Demo.CreateForm_Default</i>, the previous code will translate as follows.</p>

```java
getNavigationPath(pyWorkPage,"OOO-PGB-Work-Demo","CreateForm_Default","", "pyFlowData", false)
```

<p>The following image shows the <i>getNavigationPath</i> function which invokes the main flow (through the <i>tools.doActivity</i> function). Note that this function is passing the <i>GetNavigationPath</i> parameter as <i>true</i>.</p>

<img src="https://i.postimg.cc/Wz0yqMxS/XWURNONRNL-Get-Navigation-Path.png" alt="">

<p>The <i>tools.doActivity</i> will run the <i>perform</i> method of the flow. We can see this method through the <i>Actions > View Java</i> menu of the flow rule. The following image shows the part that is important to us. Note that this part will <i>return</i> so it won't perform the assignments.</p>

<img src="https://i.postimg.cc/R0jG1W7Y/XWURNONRNL-Java-If-Get-Navigation-Path.png" alt="">

<p>The code of the method <i>GetNavigationPath_Circum0</i> is at <a href="https://github.com/dfrankmv/pegablog/blob/a68a64238c53984e2bbb5ac2f88af453495e20f8/assets/java/XWURNONRNL-pzGeneratedJava.java#L3799-L4534" target="_blank">the line 3799 of the Generated Java</a>, but the algorithm starts at the line <a href="https://github.com/dfrankmv/pegablog/blob/a68a64238c53984e2bbb5ac2f88af453495e20f8/assets/java/XWURNONRNL-pzGeneratedJava.java#L3882" target="_blank">3882</a>.</p>

<p>This method will add pages into the <i>pyFlowData.pySteps</i>. The following is the description of the steps that run the generated java code.</p>

<p>For reference, the following image shows the internal names of the shapes in the main flow.</p>

<img src="https://i.postimg.cc/433j2HhN/XWURNONRNL-Flow-Reference.png" alt="">

<p>The following image shows the code generated for the <i>Transition2</i> connector.</p>

<img src="https://i.postimg.cc/L85Vrtv7/XWURNONRNL-Java-Transition2.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition2</i> (i.e. <i>AssignmentSF1</i>) is a valid shape (is in the <i>Valid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "y"</pre>

<p>The following image shows the code generated for the <i>AssignmentSF1</i> shape. Note that the lines 3926-3944 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages.</p>

<img src="https://i.postimg.cc/JhgYT6LP/XWURNONRNL-Java-Assignment-SF1.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>AssignmentSF1</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
<b>hashShapes[ "AssignmentSF1" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }</b>
</pre>

<p>The following image shows the additional code generated for the <i>AssignmentSF1</i> shape. This code has been generated because this shape is of type <i>Assignment</i>. The purpose of this code is to append the <i>Flow Action</i> of the assignment in the <i>pyFlowData.pyFlowActions.pyActions</i> value list.</p>

<img src="https://i.postimg.cc/K8GW43fr/XWURNONRNL-Java-Assignment-SF1-FA.png" alt="">

<p>After this code is run, the flow action, <i>StepA</i>, of the <i>AssignmentSF1</i> is added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA" }</b>
</pre>

<p>The following image shows the code generated for the <i>Transition2</i> connector.</p>

<img src="https://i.postimg.cc/c4Xm997w/XWURNONRNL-Java-Transition1.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition1</i> (i.e. <i>AssignmentSF2</i>) is a valid shape (is in the <i>Valid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
<b>hashShapes[ "AssignmentSF2" ] = "y"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA" }</pre>

<p>The following image shows the code generated for the <i>AssignmentSF2</i> shape. Note that the lines 4017-4035 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages.</p>

<img src="https://i.postimg.cc/0yXVQWbP/XWURNONRNL-Java-Assignment-SF2.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>AssignmentSF2</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
<b>hashShapes[ "AssignmentSF2" ] = "true"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
<b>pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }</b>

pyFlowData.pyFlowActions(1).pyActions = { "StepA" }
</pre>

<p>The following image shows the additional code generated for the <i>AssignmentSF2</i> shape. This code has been generated because this shape is of type <i>Assignment</i>. The purpose of this code is to append the <i>Flow Action</i> of the assignment in the <i>pyFlowData.pyFlowActions.pyActions</i> value list.</p>

<img src="https://i.postimg.cc/ydZQ2m6B/XWURNONRNL-Java-Assignment-SF2-FA.png" alt="">

<p>After this code is run, the flow action, <i>StepB</i>, of the <i>AssignmentSF2</i> is added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB" }</b>
</pre>

<p>The following image shows the code generated for the <i>Transition3</i> connector.</p>

<img src="https://i.postimg.cc/sgLJyMN2/XWURNONRNL-Java-Transition3.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition3</i> (i.e. <i>Decision1</i>) is a valid shape (is in the <i>Valid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
<b>hashShapes[ "Decision1" ] = "y"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB" }
</pre>

<p>The following image shows the code generated for the <i>Decision1</i> shape. Note that this code is shorter than the code generated for an assignment. Also, note that this decision shape is not being added to the <i>pyFlowData.pySteps</i> page list.</p>

<img src="https://i.postimg.cc/TYG7H5zM/XWURNONRNL-Java-Decision1.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>Decision1</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
<b>hashShapes[ "Decision1" ] = "true"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB" }
</pre>

<p>The following image shows the code generated for the <i>Transition5</i> connector. Note that since this connector is associated with a <i>When</i> rule, the generated code varies slightly compared to the previous transitions. In the line 4114 we can see that the code evaluates the <i>When</i> rule in order to decide if this path will be taken or not. Since at the beginning <i>.pySelected</i> is <i>false</i>, the <i>IsSelected</i> rule will return <i>false</i>, and, as a consequence, this path is not valid.</p>

<img src="https://i.postimg.cc/C1THBWYT/XWURNONRNL-Java-Transition5.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition5</i> (i.e. <i>AssignmentSF3</i>) is <b>not</b> a valid shape (is in the <i>Invalid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
<b>hashShapes[ "AssignmentSF3" ] = "n"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB" }
</pre>

<p>The following image shows the code generated for the <i>AssignmentSF3</i> shape. Note that the lines 4151-4169 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages.</p>

<img src="https://i.postimg.cc/WzVnKxf6/XWURNONRNL-Java-Assignment-SF3.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>AssignmentSF3</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
<b>hashShapes[ "AssignmentSF3" ] = "false"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
<b>pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }</b>

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB" }
</pre>

<p>The following image shows the additional code generated for the <i>AssignmentSF3</i> shape. This code has been generated because this shape is of type <i>Assignment</i>. The purpose of this code is to append the <i>Flow Action</i> of the assignment in the <i>pyFlowData.pyFlowActions.pyActions</i> value list.</p>

<img src="https://i.postimg.cc/Gtn7R49D/XWURNONRNL-Java-Assignment-SF3-FA.png" alt="">

<p>After this code is run, the flow action, <i>StepC</i>, of the <i>AssignmentSF3</i> is added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC" }</b>
</pre>

<p>The following image shows the code generated for the <i>Transition8</i> connector.</p>

<img src="https://i.postimg.cc/ZnJ8jpCw/XWURNONRNL-Java-Transition8.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition8</i> (i.e. <i>SubProcessSF1</i>) is <b>not</b> a valid shape (is in the <i>Invalid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
<b>hashShapes[ "SubProcessSF1" ] = "n"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC" }
</pre>

<p>The following image shows the code generated for the <i>SubProcessSF1</i> shape. Note that the lines 4242-4260 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages.</p>

<img src="https://i.postimg.cc/2yT4tyWr/XWURNONRNL-Java-Sub-Process-SF1.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>SubProcessSF1</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
<b>hashShapes[ "SubProcessSF1" ] = "false"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
<b>pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, ... }</b>

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC" }
</pre>

<p>The following image shows the additional code generated for the <i>SubProcessSF1Add</i> shape. This code has been generated because this shape is of type <i>Flow</i>. The purpose of this code is to embed a <i>.pyFlowData</i> in the <i>pyFlowData.pyStep(4)</i> page. This <i>.pyFlowData</i> contains the steps of the subflow.</p>

<img src="https://i.postimg.cc/TYR0cMbT/XWURNONRNL-Java-Sub-Process-SF1-Add.png" alt="">

<p>The function <i>pega_processengine_flowutilities.addSubFlowToNavPath</i> will embed a <i>.pyFlowData</i> in the current <i>.pyStep</i> page. For this example, in runtime, the line 4272-4274 will translate as follows.</p>

<pre>pega_processengine_flowutilities.addSubFlowToNavPath( pyFlowData.pySteps(4), pyWorkPage, "MySubFlow", "SubProcessSF1", "pyFlowData_MySubFlow", pyFlowData )</pre>

<p>After this code is run, a <i>.pyFlowData</i> is embedded to the <i>pyFlowData.pySteps(4)</i> page. Also the flow actions from the subflow are added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
<b>hashShapes[ "SubProcessSF1" ] = "false"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
<b>pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }</b>

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub" }</b>
</pre>

<p>The following image shows the code generated for the <i>Transition4</i> connector.</p>

<img src="https://i.postimg.cc/k5tv570v/XWURNONRNL-Java-Transition4.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition4</i> (i.e. <i>End66</i>) is <b>not</b> a valid shape (is in the <i>Invalid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
<b>hashShapes[ "END66" ] = "n"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the code generated for the <i>End66</i> shape.</p>

<img src="https://i.postimg.cc/TY978Yxy/XWURNONRNL-Java-End66.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>End66</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
<b>hashShapes[ "END66" ] = "false"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the additional code generated for the <i>End66</i> shape. This code has been generated because this shape is the <i>End</i> shape. The purpose of this code is to mark every shape that connects to this shape as an ending shape (<i>.pyIsEndingShape = true</i>). In this example, because the <i>End</i> shape is on the invalid path, this piece of code is not executed.</p>

<img src="https://i.postimg.cc/sggn3dfT/XWURNONRNL-Java-End66-Additional.png" alt="">

<p>The following image shows the code generated for the <i>Transition7</i> connector.</p>

<img src="https://i.postimg.cc/Ss6LWDNX/XWURNONRNL-Java-Transition7.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition7</i> (i.e. <i>AssignmentSF4</i>) is a valid shape (is in the <i>Valid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
<b>hashShapes[ "AssignmentSF4" ] = "y"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the code generated for the <i>AssignmentSF4</i> shape. Note that the lines 4383-4401 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages.</p>

<img src="https://i.postimg.cc/FzxZQGMt/XWURNONRNL-Java-Assignment-SF4.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>AssignmentSF4</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
<b>hashShapes[ "AssignmentSF4" ] = "true"</b>

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }
<b>pyFlowData.pySteps(5) = { .pyFlowStep="AssignmentSF4", .pyDisplay=true, ... }</b>

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the additional code generated for the <i>AssignmentSF4</i> shape. This code has been generated because this shape is of type <i>Assignment</i>. The purpose of this code is to append the <i>Flow Action</i> of the assignment in the <i>pyFlowData.pyFlowActions.pyActions</i> value list.</p>

<img src="https://i.postimg.cc/598K3SkS/XWURNONRNL-Java-Assignment-SF4-FA.png" alt="">

<p>After this code is run, the flow action, <i>ActionStub</i>, of the <i>AssignmentSF4</i> is added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
hashShapes[ "AssignmentSF4" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }
pyFlowData.pySteps(5) = { .pyFlowStep="AssignmentSF4", .pyDisplay=true, ... }

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub", "ActionStub" }</b>
</pre>

<p>The following image shows the code generated for the <i>Transition9</i> connector.</p>

<img src="https://i.postimg.cc/sfJ9x6Nq/XWURNONRNL-Java-Transition9.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the target shape of <i>Transition9</i> (i.e. <i>AssignmentSF1</i>) is a valid shape (is in the <i>Valid path</i>).</p>

<pre>hashShapes[ "Start62" ] = "true"
<b>hashShapes[ "AssignmentSF1" ] = "truey"</b>
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
hashShapes[ "AssignmentSF4" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }
pyFlowData.pySteps(5) = { .pyFlowStep="AssignmentSF4", .pyDisplay=true, ... }

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the code generated for the <i>AssignmentSF1</i> shape. Note that the lines 4474-4492 are just to set the property <i>.pyStatus</i> as current, success or future which will depend on the <i>newAssignPage</i> and the <i>pyWorkPage.pxFlow</i> pages. Also note that this generated code is repeated because the connector <i>Transition9</i>.</p>

<img src="https://i.postimg.cc/GtPMmbs5/XWURNONRNL-Java-Assignment-SF1-Again.png" alt="">

<p>After this code is run, the <i>hashShapes</i> indicates that the <i>AssignmentSF1</i> has been processed.</p>

<pre>hashShapes[ "Start62" ] = "true"
<b>hashShapes[ "AssignmentSF1" ] = "true"</b>
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
hashShapes[ "AssignmentSF4" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }
pyFlowData.pySteps(5) = { .pyFlowStep="AssignmentSF4", .pyDisplay=true, ... }
<b>pyFlowData.pySteps(6) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }</b>

pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub", "ActionStub" }
</pre>

<p>The following image shows the additional code generated for the <i>AssignmentSF1</i> shape. This code has been generated because this shape is of type <i>Assignment</i>. The purpose of this code is to append the <i>Flow Action</i> of the assignment in the <i>pyFlowData.pyFlowActions.pyActions</i> value list.</p>

<img src="https://i.postimg.cc/bvdF9XKZ/XWURNONRNL-Java-Assignment-SF1-FAAgain.png" alt="">

<p>After this code is run, the flow action, <i>StepA</i>, of the <i>AssignmentSF1</i> is added to the <i>.pyActions</i> value list.</p>

<pre>hashShapes[ "Start62" ] = "true"
hashShapes[ "AssignmentSF1" ] = "true"
hashShapes[ "AssignmentSF2" ] = "true"
hashShapes[ "Decision1" ] = "true"
hashShapes[ "AssignmentSF3" ] = "false"
hashShapes[ "SubProcessSF1" ] = "false"
hashShapes[ "END66" ] = "false"
hashShapes[ "AssignmentSF4" ] = "true"

pyFlowData.pySteps(1) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }
pyFlowData.pySteps(2) = { .pyFlowStep="AssignmentSF2", .pyDisplay=true, ... }
pyFlowData.pySteps(3) = { .pyFlowStep="AssignmentSF3", .pyDisplay=false, ... }
pyFlowData.pySteps(4) = { .pyFlowStep="SubProcessSF1", .pyDisplay=false, .pyFlowData = { .pySteps = [...] }, ... }
pyFlowData.pySteps(5) = { .pyFlowStep="AssignmentSF4", .pyDisplay=true, ... }
pyFlowData.pySteps(6) = { .pyFlowStep="AssignmentSF1", .pyDisplay=true, ... }

<b>pyFlowData.pyFlowActions(1).pyActions = { "StepA", "StepB", "StepC", "ActionStub", "ActionStub", "ActionStub", "StepA" }</b>
</pre>

<p>Finally, the following code is just to update to <i>pxFlowData.pxNodeCount</i> property.</p>

<img src="https://i.postimg.cc/cJKX8RnY/XWURNONRNL-Java-Final-Code.png" alt="">

<p>The following animation shows the result of the algorithm at the beginning of the case.</p>

<img data-gifffer="https://i.postimg.cc/gc48mwwP/XWURNONRNL-Result-Clipboard.gif" />
