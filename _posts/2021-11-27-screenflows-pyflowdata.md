<h2>Screenflows &mdash; pyFlowData</h2>

<p>The <i>pyFlowData</i> page is used to show the navigation links in a Screen Flow.</p>

<p>The following image shows the navigation links (using the <i>TreeNavigation7</i> harness). Note that the main Screen Flow has a sub-flow called <i>MySubFlow</i> with two steps.</p>

<img src="img/XWURNONRNL/ScreenFlow.png" alt="">

<p>The following image shows their corresponding <i>pyFlowData</i> page in the clipboard.</p>

<img src="img\XWURNONRNL\Clipboard.png" alt="">

<h3>How is the pyFlowData populated?</h3>

<p>Using the tracer we can get the step where the <i>pyFlowData</i> is populated. The activity responsible for this is <i>Work-.GetFlowData</i> which uses the following function in a Java step.</p>

<pre>pega_processengine_flowutilities.getNavigationPath(interestPage, strFlowClass, strFlowType, pg_pxFlow.getString(".pyParentFlowPath"), "pyFlowData", tools.getParamAsBoolean(PropertyInfo.TYPE_TRUEFALSE, "deferredLoad"));</pre>

<p>As an example, using the main flow <i>OOO-PGB-Work-Demo.CreateForm_Default</i>, the previous code will translate as follows.</p>

<pre>getNavigationPath(pyWorkPage,"OOO-PGB-Work-Demo","CreateForm_Default","", "pyFlowData", false) </pre>

<p>The following image shows the <i>getNavigationPath</i> function which invokes the main flow (through the <i>tools.doActivity</i> function). Note that this function is passing the <i>GetNavigationPath</i> parameter as <i>true</i>.</p>

<img src="img\XWURNONRNL\GetNavigationPath.png" alt="">

<p>The <i>tools.doActivity</i> will run the <i>perform</i> method of the flow. We can see this method through the <i>Actions > View Java</i> menu of the flow rule. The following image shows the part that is important to us. Note that this part will <i>return</i> so it won't perform the assignments.</p>

<img src="img\XWURNONRNL\JavaIfGetNavigationPath.png" alt="">

<p>The code of the method <i>GetNavigationPath_Circum0</i> is at the end of this post.</p>

<p>This method will add pages into the <i>pyFlowData.pySteps</i>. The following is the description of the steps that run the generated java code.</p>

<p>For reference, the following image shows the internal names of the shapes in the main flow.</p>

<img src="img\XWURNONRNL\MainFlow.png" alt="">

<p>The following image shows the generated java code for a transition (an arrow in the flow). This code just validates the next shape (put <i>hash[nextShape] = "y"</i> if it's valid)</p>

<img src="img\XWURNONRNL\MainAlgorithm.png" alt="">

<p>The following image shows the generated java code for a shape. This code appends a page into <i>pyFlowData.pySteps</i>. If the previous transition is not valid, then the page will have <i>.pyDisplay = false</i>. Depending on the current <i>newAssignPage</i>, the page will have the <i>.pyStatus</i> value set to <i>current</i> or <i>success</i>.</p>

<img src="img\XWURNONRNL\JavaShape.png" alt="">

<div class="note">
  <p><b>For an assignment shape</b>, in addition to adding a new page to the <i>pyFlowData.pySteps</i>, more code is added to append the flow action to the <i>pyFlowData.pyFlowActions().pyActions</i> page group.</p> 

  <img src="img\XWURNONRNL\AdditionalJavaAssignment.png" alt="">
</div>

<div class="note">
  <p><b>For a Subflow shape</b>, in addition to adding a new page to the <i>pyFlowData.pySteps</i>, more code is added to embed a <i>.pyFlowData</i> page to the <i>pyFlowData.pySteps(&lt;CURRENT>)</i> page. This embedded <i>.pyFlowData</i> page contains the data/steps of the subflow.</p> 

  <img src="img\XWURNONRNL\AdditionalJavaSubflow.png" alt="">

  <p>The previous code only appears when the <i>Subprocess has navigation links</i> option selected.</p>

  <img src="img\XWURNONRNL\SubprocessHasNav.png" alt="">
</div>


```java
private void GetNavigationPath_circum0(String pageName,
                                       String parentFlowPath, boolean deferredLoad)
{
    ClipboardPage newAssignPage = tools.findPage("newAssignPage");
    if (newAssignPage == null)
        return;
    ClipboardPage pgNavPath = null;
    ClipboardPage pgFlowData = tools.findPage("pyFlowData");
    if (pgFlowData == null)
    {
        // The pyFlowData page needs to be created
        pgFlowData = tools.createPage("Code-Flow-Navigation", "pyFlowData");
        pgNavPath = pgFlowData;
    }
    else if (pageName.equals("pyFlowData"))
    {
        // Now we need to check whether we can reuse this page or not.  If we can't, we have to rebuild it entirely, otherwise we can tweak what we need to using updateNavigatioPath.
        // We have to rebuild it if it has connector whens (pyAlwaysRebuild), it is running on a different Flow rule, or a different primary page.
        String pgFlowDataIntPage = pgFlowData.getString("pyInterestPage");
        String curIntPage = interestPage.getReference();
        boolean isTopLevelIntPage = (pgFlowDataIntPage.equals("") && !interestPage
                                     .isEmbedded()); // pyFlowData is top level because pyInterestPage is blank, and our current primary page is at top too
        boolean isSameIntPage = (isTopLevelIntPage || pgFlowDataIntPage
                                 .equals(curIntPage)); //  Both top level or both pointing to the same primary page
        if (pgFlowData.getProperty("pyAlwaysRebuild").toBoolean()
                || !pgFlowData.getString("pyFlowInsKey")
                .equals(mFlowHandle) || !isSameIntPage)
        {
            // Either we've been instructed to rebuild it from scratch rather than dynamically update due to when rules, or it is an old page from a previous flow or previous split
            pgFlowData = tools.createPage("Code-Flow-Navigation",
                                          "pyFlowData");
            pgNavPath = pgFlowData;
        }
        else
        {
            // The pyFlowData page already exists and it is for this flow; we merely need to update it
            pega_processengine_flowutilities.updateNavigationPath(pageName,
                    "", interestPage, newAssignPage);
            return;
        }
    }
    else
    {
        // We're adding a subflow onto the page, build out the embedded structure
        pgNavPath = pgFlowData.getPage(pageName);
    }

    pgNavPath.putString("pyParentFlowPath", parentFlowPath);
    pgNavPath.putString("pyFlowType", "CreateForm_Default");
    pgNavPath.putString("pyClassName", "OOO-PGB-Work-Demo");
    pgNavPath.putString("pyLabel", "Create");
    pgNavPath.putString("pyFlowInsKey", mFlowHandle);
    pgNavPath.putString("pyDeferErrors", "false");
    pgNavPath.getProperty("pyAlwaysRebuild").setValue(false);
    pgNavPath.getProperty("pyIsScreenFlow").setValue(
        pega_procom_pegaprocomutilities.IsScreenFlow("ScreenFlow"));
    pgNavPath.getProperty("pyDeferredLoad").setValue(deferredLoad);
    // workPage & currentFlow vars are needed for createNewParamsPage called by EvaluateConnector
    if (interestPage.isEmbedded())
    {
        pgNavPath.putString("pyInterestPage", interestPage.getReference());
        workPage = interestPage.getTopLevelPage();
    }
    else
    {
        // Since we are at the top level of the work object, we don't set pyInterestPage
        workPage = interestPage;
    }
    currentFlow = tools.createPage("Embed-PegaEPRO-FlowPage", "");

    ClipboardPage pgStep = null;
    ClipboardProperty cpSteps = pgNavPath.getProperty(".pySteps");
    ClipboardProperty cpNodeCount = pgNavPath.getProperty(".pxNodeCount");
    ClipboardProperty subFlowPrimaryPageRef = null;
    ClipboardPage subFlowPrimaryPage = null;
    String flowClass = "";
    String thisParentFlowPath;
    boolean bGetSubflowInfo;
    boolean bIsInFlowPath;
    boolean bFoundFlowPath = false;
    String flowNameToSearchForTaskIn = "";
    flowNameToSearchForTaskIn = newAssignPage.getString(".pxFlowName");
    ClipboardProperty cp_pyFlowActions = pgFlowData
                                         .getProperty("pyFlowActions");
    boolean bDeferLoadingSubFlows = pgFlowData
                                    .getProperty("pyDeferredLoad").toBoolean();
    String strFlowAction;
    boolean bThisShapeIsDisplayable = false;
    String hashShapeValue;
    String fromShapeValue;
    String toShapeValue;
    String newHashShapeValue;
    java.util.HashMap hashShapes = new java.util.HashMap();
    java.util.Vector feedingShapes;
    java.util.Iterator itSteps;
    hashShapes.put("Start62", "true");
    // *****************************************************************************
    // We need to determine if this connector Transition2 comes from a valid path
    // First we check if the connector's origin shape is valid
    fromShapeValue = (String) hashShapes.get("Start62");
    toShapeValue = (String) hashShapes.get("AssignmentSF1");
    if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1)
    {
        // Next we need to check if a higher likelihood connector hasn't already been taken
        if (fromShapeValue.indexOf("taken") > -1)
        {
            // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
            newHashShapeValue = "n";
        }
        else
        {
            // This connector doesn't have a conditional expression associated with it, so it is valid
            newHashShapeValue = "y";
        }
    }
    else
    {
        // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
        newHashShapeValue = "n";
    }
    // Now concatenate this connector's value onto the destination shape's hash
    if (toShapeValue != null)
        newHashShapeValue = toShapeValue + newHashShapeValue;
    hashShapes.put("AssignmentSF1", newHashShapeValue);

    // *****************************************************************************
    // Examining step "AssignmentSF1"
    bThisShapeIsDisplayable = true;

    // Now determine whether this shape lies down a valid path
    // At least one connector leading into this shape must have set a 'y' in the hash entry
    hashShapeValue = (String) hashShapes.get("AssignmentSF1");
    if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1)
    {
        // It is easier for checking the connectors to replace the 'y' with 'true'
        hashShapes.put("AssignmentSF1", "true");
    }
    else
    {
        bThisShapeIsDisplayable = false;
        hashShapes.put("AssignmentSF1", "false");
    }
    bGetSubflowInfo = true;
    subFlowPrimaryPageRef = null; // default back to null
    pgStep = pgNavPath.getProperty(".pySteps(<APPEND>)").getPageValue();
    pgStep.putString("pyFlowStep", "AssignmentSF1");
    pgStep.putString("pyStepLabel", "Step A");
    pgStep.putString("pyStepType", "ASSIGNMENT");
    bIsInFlowPath = pega_procom_breadcrumbtrail.isInFlowPath2(tools,
                    "AssignmentSF1", "CreateForm_Default", parentFlowPath,
                    flowNameToSearchForTaskIn, interestPage.getTopLevelPage());
    if (bIsInFlowPath)
    {
        if (!bFoundFlowPath)
        {
            bFoundFlowPath = true;
            flowNameToSearchForTaskIn = tools
                                        .getParamValue("FoundInFlowName");
        }
        if (newAssignPage.getString("pxTaskName").equals("AssignmentSF1")
                && newAssignPage.getString("pyFlowType").equals(
                    "CreateForm_Default")
                && newAssignPage.getString("pyParentFlowPath").equals(
                    parentFlowPath))
            pgStep.putString("pyStatus", "current");
        else
            pgStep.putString("pyStatus", "success");
    }
    else
        pgStep.putString("pyStatus", "future");
    pgStep.putString("pyDisabled", "false");
    pgStep.putString("pyFAProcessOnJump", "false");
    pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

    strFlowAction = "ActionStub";
    pgStep.putString("pyFlowAction", strFlowAction);
    if (!strFlowAction.equals(""))
    {
        ClipboardPage pg = null;
        java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
        while (iter_pyFlowActions.hasNext())
        {
            ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                                                .next();
            ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
            if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference()))
            {
                pg = pg_pyFlowAction;
                break;
            }
        }

        if (pg == null)
        {
            pg = tools.createPage("Embed-Action", "");
            pg.putString("pyInterestPage", interestPage.getReference());
            pg.getProperty("pyActions").add(strFlowAction);
            cp_pyFlowActions.add(pg);
        }
        else
            pg.getProperty("pyActions").add(strFlowAction);
    }

    // *****************************************************************************
    // We need to determine if this connector Transition1 comes from a valid path
    // First we check if the connector's origin shape is valid
    fromShapeValue = (String) hashShapes.get("AssignmentSF1");
    toShapeValue = (String) hashShapes.get("AssignmentSF2");
    if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1)
    {
        // Next we need to check if a higher likelihood connector hasn't already been taken
        if (fromShapeValue.indexOf("taken") > -1)
        {
            // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
            newHashShapeValue = "n";
        }
        else
        {
            // This connector doesn't have a conditional expression associated with it, so it is valid
            newHashShapeValue = "y";
        }
    }
    else
    {
        // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
        newHashShapeValue = "n";
    }
    // Now concatenate this connector's value onto the destination shape's hash
    if (toShapeValue != null)
        newHashShapeValue = toShapeValue + newHashShapeValue;
    hashShapes.put("AssignmentSF2", newHashShapeValue);

    // *****************************************************************************
    // Examining step "AssignmentSF2"
    bThisShapeIsDisplayable = true;

    // Now determine whether this shape lies down a valid path
    // At least one connector leading into this shape must have set a 'y' in the hash entry
    hashShapeValue = (String) hashShapes.get("AssignmentSF2");
    if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1)
    {
        // It is easier for checking the connectors to replace the 'y' with 'true'
        hashShapes.put("AssignmentSF2", "true");
    }
    else
    {
        bThisShapeIsDisplayable = false;
        hashShapes.put("AssignmentSF2", "false");
    }
    bGetSubflowInfo = true;
    subFlowPrimaryPageRef = null; // default back to null
    pgStep = pgNavPath.getProperty(".pySteps(<APPEND>)").getPageValue();
    pgStep.putString("pyFlowStep", "AssignmentSF2");
    pgStep.putString("pyStepLabel", "Step B");
    pgStep.putString("pyStepType", "ASSIGNMENT");
    bIsInFlowPath = pega_procom_breadcrumbtrail.isInFlowPath2(tools,
                    "AssignmentSF2", "CreateForm_Default", parentFlowPath,
                    flowNameToSearchForTaskIn, interestPage.getTopLevelPage());
    if (bIsInFlowPath)
    {
        if (!bFoundFlowPath)
        {
            bFoundFlowPath = true;
            flowNameToSearchForTaskIn = tools
                                        .getParamValue("FoundInFlowName");
        }
        if (newAssignPage.getString("pxTaskName").equals("AssignmentSF2")
                && newAssignPage.getString("pyFlowType").equals(
                    "CreateForm_Default")
                && newAssignPage.getString("pyParentFlowPath").equals(
                    parentFlowPath))
            pgStep.putString("pyStatus", "current");
        else
            pgStep.putString("pyStatus", "success");
    }
    else
        pgStep.putString("pyStatus", "future");
    pgStep.putString("pyDisabled", "false");
    pgStep.putString("pyFAProcessOnJump", "false");
    pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

    strFlowAction = "ActionStub";
    pgStep.putString("pyFlowAction", strFlowAction);
    if (!strFlowAction.equals(""))
    {
        ClipboardPage pg = null;
        java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
        while (iter_pyFlowActions.hasNext())
        {
            ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                                                .next();
            ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
            if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference()))
            {
                pg = pg_pyFlowAction;
                break;
            }
        }

        if (pg == null)
        {
            pg = tools.createPage("Embed-Action", "");
            pg.putString("pyInterestPage", interestPage.getReference());
            pg.getProperty("pyActions").add(strFlowAction);
            cp_pyFlowActions.add(pg);
        }
        else
            pg.getProperty("pyActions").add(strFlowAction);
    }

    // *****************************************************************************
    // We need to determine if this connector Transition3 comes from a valid path
    // First we check if the connector's origin shape is valid
    fromShapeValue = (String) hashShapes.get("AssignmentSF2");
    toShapeValue = (String) hashShapes.get("SubProcessSF1");
    if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1)
    {
        // Next we need to check if a higher likelihood connector hasn't already been taken
        if (fromShapeValue.indexOf("taken") > -1)
        {
            // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
            newHashShapeValue = "n";
        }
        else
        {
            // This connector doesn't have a conditional expression associated with it, so it is valid
            newHashShapeValue = "y";
        }
    }
    else
    {
        // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
        newHashShapeValue = "n";
    }
    // Now concatenate this connector's value onto the destination shape's hash
    if (toShapeValue != null)
        newHashShapeValue = toShapeValue + newHashShapeValue;
    hashShapes.put("SubProcessSF1", newHashShapeValue);

    // *****************************************************************************
    // Examining step "SubProcessSF1"
    bThisShapeIsDisplayable = true;

    // Now determine whether this shape lies down a valid path
    // At least one connector leading into this shape must have set a 'y' in the hash entry
    hashShapeValue = (String) hashShapes.get("SubProcessSF1");
    if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1)
    {
        // It is easier for checking the connectors to replace the 'y' with 'true'
        hashShapes.put("SubProcessSF1", "true");
    }
    else
    {
        bThisShapeIsDisplayable = false;
        hashShapes.put("SubProcessSF1", "false");
    }
    bGetSubflowInfo = true;
    subFlowPrimaryPageRef = null; // default back to null
    pgStep = pgNavPath.getProperty(".pySteps(<APPEND>)").getPageValue();
    pgStep.putString("pyFlowStep", "SubProcessSF1");
    pgStep.putString("pyStepLabel", "MySubFlow");
    pgStep.putString("pyStepType", "FLOW");
    bIsInFlowPath = pega_procom_breadcrumbtrail.isInFlowPath2(tools,
                    "SubProcessSF1", "CreateForm_Default", parentFlowPath,
                    flowNameToSearchForTaskIn, interestPage.getTopLevelPage());
    if (bIsInFlowPath)
    {
        if (!bFoundFlowPath)
        {
            bFoundFlowPath = true;
            flowNameToSearchForTaskIn = tools
                                        .getParamValue("FoundInFlowName");
        }
        if (newAssignPage.getString("pxTaskName").equals("SubProcessSF1")
                && newAssignPage.getString("pyFlowType").equals(
                    "CreateForm_Default")
                && newAssignPage.getString("pyParentFlowPath").equals(
                    parentFlowPath))
            pgStep.putString("pyStatus", "current");
        else
            pgStep.putString("pyStatus", "success");
    }
    else
        pgStep.putString("pyStatus", "future");
    pgStep.putString("pyDisabled", "false");
    pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

    subFlowPrimaryPage = interestPage;
    flowClass = interestPage.getString(".pxObjClass");
    if (parentFlowPath.length() > 0)
        thisParentFlowPath = parentFlowPath + "/"
                             + pgStep.getString("pyFlowStep");
    else
        thisParentFlowPath = pgStep.getString("pyFlowStep");
    if (bGetSubflowInfo)
        pega_processengine_flowutilities.addSubFlowToNavPath(pgStep,
                subFlowPrimaryPage, "MySubFlow", thisParentFlowPath,
                pageName + "_MySubFlow", pgNavPath);
    else
    {
        // We need to record enough information here to call getNavigationPath later
        ClipboardPage pyFlowData = pgStep.getProperty(".pyFlowData")
                                   .getPageValue();
        pyFlowData.putString("pyParentFlowPath", thisParentFlowPath);
        pyFlowData.putString("pyFlowType", "MySubFlow");
        pyFlowData.putString("pyInterestPage", "");
    }

    // *****************************************************************************
    // We need to determine if this connector Transition4 comes from a valid path
    // First we check if the connector's origin shape is valid
    fromShapeValue = (String) hashShapes.get("SubProcessSF1");
    toShapeValue = (String) hashShapes.get("END66");
    if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1)
    {
        // Next we need to check if a higher likelihood connector hasn't already been taken
        if (fromShapeValue.indexOf("taken") > -1)
        {
            // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
            newHashShapeValue = "n";
        }
        else
        {
            // This connector doesn't have a conditional expression associated with it, so it is valid
            newHashShapeValue = "y";
        }
    }
    else
    {
        // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
        newHashShapeValue = "n";
    }
    // Now concatenate this connector's value onto the destination shape's hash
    if (toShapeValue != null)
        newHashShapeValue = toShapeValue + newHashShapeValue;
    hashShapes.put("END66", newHashShapeValue);

    // *****************************************************************************
    // Examining step "END66"
    bThisShapeIsDisplayable = false;

    // Now determine whether this shape lies down a valid path
    // At least one connector leading into this shape must have set a 'y' in the hash entry
    hashShapeValue = (String) hashShapes.get("END66");
    if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1)
    {
        // It is easier for checking the connectors to replace the 'y' with 'true'
        hashShapes.put("END66", "true");
    }
    else
    {
        bThisShapeIsDisplayable = false;
        hashShapes.put("END66", "false");
    }

    if (((String) hashShapes.get("END66")).equals("true"))
    {
        // The last shape has no exiting connectors and is on a valid path; mark all the shapes leading to it as ending shapes.
        feedingShapes = new java.util.Vector();
        itSteps = cpSteps.iterator();
        while (itSteps.hasNext())
        {
            ClipboardProperty cpStep = (ClipboardProperty) itSteps.next();
            String strStep = cpStep.getPageValue()
                             .getProperty(".pyFlowStep").getStringValue();
            if (feedingShapes.contains(strStep))
            {
                cpStep.getPageValue().getProperty(".pyIsEndingShape")
                .setValue(true);
            }
        }
        if (cpSteps.size() > 0)
            cpSteps.getPropertyValue(cpSteps.size())
            .getProperty(".pyIsEndingShape").setValue(true);
    }

    // Now we iterate through pySteps and set pxNodeCount
    int nodecount = cpSteps.size();
    java.util.Iterator iter = cpSteps.iterator();
    while (iter.hasNext())
    {
        ClipboardProperty stepRef = (ClipboardProperty) iter.next();
        ClipboardPage pg_Step = stepRef.getPageValue();
        String steptype = pg_Step.getString(".pyStepType");
        if (steptype.equals("FLOW") || steptype.equals("SPLITFOREACH"))
            nodecount += pg_Step.getProperty(".pyFlowData.pxNodeCount")
                         .toInteger();
    }
    cpNodeCount.setValue(nodecount);
}
```
