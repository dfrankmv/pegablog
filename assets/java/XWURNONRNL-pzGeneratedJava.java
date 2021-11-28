package com.pegarules.generated.testgen;

/*
 * Copyright (c) 2021 Pegasystems Inc.
 * All rights reserved.
 *
 * This software is governed by a License Agreement that restricts its
 * use.  The software contains valuable trade secrets and proprietary
 * information of Pegasystems Inc and is protected by federal copyright
 * law.  It may not be copied, modified, translated or distributed in any
 * form or medium, disclosed to third parties or used in any manner not
 * expressly provided for in said License Agreement except with written
 * authorization from Pegasystems Inc.
 */
import com.pega.ibm.icu.math.BigDecimal;
import com.pega.pegarules.priv.AbstractFUASupport;
import com.pega.pegarules.priv.FUAUtil;
import com.pega.pegarules.priv.LogHelper;
import com.pega.pegarules.priv.PegaAPI;
import com.pega.pegarules.priv.clipboard.PegaClipboardPage;
import com.pega.pegarules.priv.database.*;
import com.pega.pegarules.priv.generator.DependentRuleInfo;
import com.pega.pegarules.pub.*;
import com.pega.pegarules.pub.clipboard.*;
import com.pega.pegarules.pub.context.*;
import com.pega.pegarules.pub.database.*;
import com.pega.pegarules.pub.dictionary.*;
import com.pega.pegarules.pub.generator.RuleNotFoundException;
import com.pega.pegarules.pub.infengine.*;
import com.pega.pegarules.pub.presence.*;
import com.pega.pegarules.pub.runtime.*;
import com.pega.pegarules.pub.services.*;
import com.pega.pegarules.pub.util.*;
import com.pega.platform.clipboard.query.PageListQuery;
import com.pega.platform.clipboard.query.PageQuery;
import com.pega.platform.clipboard.query.PropertyQuery;
import com.pega.platform.clipboard.query.ScalarListQuery;
import com.pega.platform.clipboard.query.ScalarValueQuery;
import com.pega.platform.executionengine.runtime.Loadtime;
import com.pegarules.generated.*;
import java.util.*;

public class Rule_Obj_Flow_OOO_PGB_Work_Demo_CreateForm_Default_Action_20211127T110301_884_GMT
        extends com.pega.pegarules.priv.AbstractFUASupport implements Activity,
        FUASupport {
    private static final LogHelper oLog = new LogHelper(
            "Rule_Obj_Flow.CreateForm_Default.OOO_PGB_Work_Demo.Action");

    public Rule_Obj_Flow_OOO_PGB_Work_Demo_CreateForm_Default_Action_20211127T110301_884_GMT(
            PegaAPI aContext) {
        tools = aContext;
        pega = aContext;
    }

    public void perform() {
        mTraceInfo = oTraceInfo_0;
        mRuleSet = "PGB";
        mFlowHandle = "RULE-OBJ-FLOW OOO-PGB-WORK-DEMO CREATEFORM_DEFAULT #20211127T110301.884 GMT";

        flowType = "CreateForm_Default";

        // The interestPage of the flow is the primary page we were called with
        interestPage = tools.getPrimaryPage();
        myStepPage = interestPage;
        bDraftMode = false;

        // If the parameter GetWorkCreateInfo is set, this means the flow is not being
        // called to further the flow, but rather to get the flow's work object creation properties
        String getWorkCreateInfo = tools.getParamValue("GetWorkCreateInfo");
        if (getWorkCreateInfo != null && getWorkCreateInfo.equals("true")) {
            tools.putParamValue("SkipNewHarness", "true");
            tools.putParamValue("WorkPartiesRule", "Default");
            StringMap keys = new HashStringMap();
            keys.putString("pxObjClass", "Rule-Obj-CaseType");
            keys.putString("pyPurpose", "pyDefault");
            tools.putParamValue("pyCaseTypePurpose", "GETWORKPARTIESRULE");
            try {
                tools.doActivity(keys, tools.getPrimaryPage(), null);
            } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                tools.getStepStatus().backoutWorst();
            }
            tools.getParameterPage().remove("pyCaseTypePurpose");
            //If the parameter, ErrorMessage, has the value, CaseTypeIsAbstract, remove ErrorMessage from the parameter page.
            if (tools.getParamValue("ErrorMessage")
                    .equals("CaseTypeIsAbstract")) {
                tools.getParameterPage().remove("ErrorMessage");
            }
            tools.putParamValue("CreatesTemporaryObject", "false");
            String strCoveredByClass = tools.getParamValue("CoverClass");
            if (!strCoveredByClass.equals("")) {
                tools.putParamValue("StartingHarness", "NewCovered");
                tools.putParamValue("StartingModel", "pyDefault");
            } else {
                tools.putParamValue("StartingHarness", "New");
                tools.putParamValue("StartingModel", "pyDefault");
            }
            tools.putParamValue("FlowHandle", mFlowHandle);
            return;
        }

        // If the parameter AddCoveredToProcessList is set, this means the flow is not being
        // called to further the flow, but rather to add this flow to the ProcessList page
        String addCoveredToProcessList = tools
                .getParamValue("AddCoveredToProcessList");
        if (addCoveredToProcessList != null
                && addCoveredToProcessList.equals("true")) {
            tools.putParamValue("Success", "false");
            return;
        }

        // If the parameter GetPromptStream is set, this means the flow is not being
        // called to further the flow, but rather to get the parameter prompt html
        String getPromptStream = tools.getParamValue("GetPromptStream");
        if (getPromptStream != null && getPromptStream.equals("true")) {
            tools.putParamValue("PromptStream", "");
            return;
        }

        // If the parameter GetFlowInfo is set, populate the flow pzInsKey and pxInsName
        // called to further the flow, but rather to get the parameter prompt html
        String GetFlowInfo = tools.getParamValue("GetFlowInfo");
        if (GetFlowInfo != null && GetFlowInfo.equals("true")) {
            tools.putParamValue("FlowHandle", mFlowHandle);
            tools.putParamValue("FlowInsName",
                    "OOO-PGB-WORK-DEMO!CREATEFORM_DEFAULT");
            return;
        }

        // If the parameter GetStructureType is set, this means the flow is not being
        // called to further the flow, but rather to get the parameter StructureType
        String getStructureType = tools.getParamValue("GetStructureType");
        if (getStructureType != null && getStructureType.equals("true")) {
            tools.putParamValue("StructureType", "Complex");
            return;
        }

        // If the parameter CheckFlowSecurity is set, this means the flow is not being
        // called to further the flow, but rather to check if it can be called
        String strCheckFlowSecurity = tools.getParamValue("CheckFlowSecurity");
        if (strCheckFlowSecurity != null && strCheckFlowSecurity.equals("true")) {
            if (CheckFlowSecurity_circum0())
                tools.putParamValue("HasSecurity", "true");
            else
                tools.putParamValue("HasSecurity", "false");
            tools.putParamValue("CanStartInteractively", "false");
            tools.putParamValue("pyLabel", "Create");
            return;
        }

        // If the parameter GetCoordinates is set, this means the flow is not being
        // called to further the flow, but rather to get shape coordinate information
        String getCoordinates = tools.getParamValue("GetCoordinates");
        if (getCoordinates != null && getCoordinates.equals("true")) {
            String strPageName = tools.getParamValue("pageName");
            GetCoordinates_circum0(strPageName);
            return;
        }

        // If the parameter GetNavigationPath is set, this means the flow is not being
        // called to further the flow, but rather to get navigation path information
        String getNavigationPath = tools.getParamValue("GetNavigationPath");
        if (getNavigationPath != null && getNavigationPath.equals("true")) {
            String strPageName = tools.getParamValue("pageName");
            String strParentPath = tools.getParamValue("parentFlowPath");
            boolean deferredLoad = tools.getParamAsBoolean(
                    PropertyInfo.TYPE_TRUEFALSE, "deferredLoad");
            GetNavigationPath_circum0(strPageName, strParentPath, deferredLoad);
            return;
        }

        // If the parameter GetOrderedFlowSteps is set, this means the flow is not being
        // called to further the flow, but rather to get shape coordinate information
        String getOrderedFlowSteps = tools.getParamValue("GetOrderedFlowSteps");
        if (getOrderedFlowSteps != null && getOrderedFlowSteps.equals("true")) {
            String strStepsPageName = tools.getParamValue("FlowStepPage");
            GetOrderedFlowSteps_circum0(strStepsPageName);
            return;
        }

        // If the parameter IsConnectorFlowAction is set, this means the flow is not being
        // called to further the flow, but rather to find out if the specified action is a connector
        String IsConnectorFlowAction = tools
                .getParamValue("IsConnectorFlowAction");
        if (IsConnectorFlowAction != null
                && IsConnectorFlowAction.equals("true")) {
            String strTaskName = tools.getParamValue("taskName");
            String strAction = tools.getParamValue("actionName");
            boolean bIsConnector = false;
            if (strTaskName.equals("AssignmentSF4"))
                bIsConnector = IsConnectorFlowAction_AssignmentSF4_circum0(strAction);
            else if (strTaskName.equals("AssignmentSF1"))
                bIsConnector = IsConnectorFlowAction_AssignmentSF1_circum0(strAction);
            else if (strTaskName.equals("AssignmentSF2"))
                bIsConnector = IsConnectorFlowAction_AssignmentSF2_circum0(strAction);
            else if (strTaskName.equals("AssignmentSF3"))
                bIsConnector = IsConnectorFlowAction_AssignmentSF3_circum0(strAction);
            tools.putParamValue("IsConnectorRetVal",
                    new Boolean(bIsConnector).toString());
            return;
        }

        // If the parameter BuildFlowPage is set, this means the flow is not being
        // called to further the flow, but rather to build an embedded flow page.
        // We will let processing continue, but we will set the buildFlowPage flag.
        // This will insure that the flow page is built, but no execution occurs.
        String strBuildFlowPage = tools.getParamValue("BuildFlowPage");
        if (strBuildFlowPage != null && strBuildFlowPage.equals("true")) {
            // pyNextFlowStep is set to the subflow shape whose flow page we want to build.
            buildFlowPage = true;
        }

        // Load the endingTasks Hashtable
        endingTasks.put("END66", "END66");

        // We know which flow to run based on the flowName parameter which
        // is the subscript of the embedded flow page
        flowName = tools.getParamValue("flowName");
        if (flowName.equals("")) {
            tools.getPrimaryPage().addMessage("Flow_MissingFlowName");
            LogTraceMessage("flowName parameter not provided");
            return;
        }

        // get the work page (which holds the embedded flow page)
        workPageName = tools.getParamValue("ReferencePageName");
        if (workPageName.length() > 0)
            workPage = tools.findPage(workPageName);
        else if (tools.getPrimaryPage().isEmbedded())
            // if the flow is called with an interest page, it is usually an embedded page
            workPage = tools.getPrimaryPage().getTopLevelPage();
        else
            // just set the workPage to the primary page
            workPage = tools.getPrimaryPage();

        if (workPage == null) {
            tools.getPrimaryPage().addMessage("Flow_NullWorkPage");
            LogTraceMessage("Referenced work page not found");
            return;
        }

        // set the new object flag; we pass this value into RecalculateAndSave and called flows
        newObject = tools.getParamAsBoolean(PropertyInfo.TYPE_TRUEFALSE,
                "pyNewObject");
        // now remove pyNewObject from the parameter page so it doesn't get copied into the flow's
        tools.getParameterPage().remove("pyNewObject");

        // Check if only a temporary object
        isTemporaryObject = workPage.getProperty(".pyTemporaryObject")
                .toBoolean();

        // We need to remember the caller's parameter page to set FlowHasEnded
        callerParameterPage = tools.getParameterPage();

        // get the embedded flow page
        currentFlowRef = workPage.getIfPresent(".pxFlow(" + flowName + ")");
        if (currentFlowRef == null) {
            workPage.addMessage("Flow_NotPresent\t" + flowName);
            LogTraceMessage("Embedded flow page " + flowName
                    + " not present on work page " + workPageName);
            return;
        }
        currentFlow = currentFlowRef.getPageValue();
        pyFlowParametersPage = currentFlow.getProperty(".pyFlowParameters")
                .getPageValue();
        String pyLastFlowStep = currentFlow.getString(".pyLastFlowStep").trim();
        pyNextFlowStep = currentFlow.getString(".pyNextFlowStep").trim();
        if (buildFlowPage && pyLastFlowStep.equals(pyNextFlowStep)) {
            // The flow has been called to rebuild a subflow that is already present.
            // Now we need to loop through pxFlow pages & see if we can find the existing child flow
            String jumpToSubscript = currentFlow
                    .getString(".pyJumpToSubscript");
            boolean bFoundChildFlow = false;
            ClipboardProperty pxFlow = workPage.getProperty(".pxFlow");
            java.util.Iterator itFlow = pxFlow.iterator();
            while (itFlow.hasNext()) {
                ClipboardProperty cpFlow = (ClipboardProperty) itFlow.next();
                ClipboardPage pgFlow = cpFlow.getPageValue();
                String pyIssuedFromFlow = pgFlow.getString(".pyIssuedFromFlow");
                String pyIssuedFromSplitSubscript = pgFlow
                        .getString(".pyIssuedFromSplitSubscript");
                if (pyIssuedFromFlow.equals(flowName)
                        && (pyIssuedFromSplitSubscript.equals("") || pyIssuedFromSplitSubscript
                                .equals(jumpToSubscript))) {
                    callerParameterPage.putString("newFlowPageName",
                            pgFlow.getString(".pxSubscript"));
                    currentFlow.putString(".pyNextFlowStep", "");
                    currentFlow.putString(".pyJumpToSubscript", "");
                    LogTraceMessage("Called in buildFlowPage mode; aborting because child flow "
                            + callerParameterPage.getString("newFlowPageName")
                            + " already exists");
                    return;
                }
            }
        }

        // We need to know the work page's information, because we have to make
        // sure it doesn't get overwritten or removed in a utility
        workPageName = workPage.getName();
        if (isTemporaryObject)
            workPageHandle = "";
        else {
            try {
                workPageHandle = tools.getDatabase().getHandle(workPage);
            } catch (DatabaseException de) {
                ProblemFlow(de, pyLastFlowStep);
                return;
            }
        }
        workUpdateDateTime = workPage.getString(".pxUpdateDateTime");

        if (workPage.getProperty(".pxSuspended").toBoolean() == true) {
            boolean isInvestigative = currentFlow.getProperty(
                    ".pxIsInvestigative").toBoolean();
            if (!isInvestigative
                    || (isInvestigative && !tools.getAuthorizationHandle()
                            .havePrivilege(tools, "ReviewPolicyOverrides",
                                    workPage.getClassName(), workPage))) {
                // The user is trying to resume a suspended flow
                workPage.addMessage("Flow_ResumeWhileWorkSuspendedFail",
                        "pyMessageLabel", "@baseclass");
                return;
            }
        }

        // see if we need to set the RethrowConnectionProblem flag
        String strRethrowConnectionProblem = tools
                .getParamValue("RethrowConnectionProblem");
        if (strRethrowConnectionProblem != null
                && strRethrowConnectionProblem.equals("true")) {
            RethrowConnectionProblem = true;
            InRetryConnection = true;
        }

        if (!CheckFlowSecurity_circum0()) {
            throw new PRAppRuntimeException("Pega-ProcessEngine", -1.0,
                    tools.getLocalizedTextForString("pyCaption",
                            "Unabletoauthorizeflowexecution") + mFlowHandle);
        }

        // If the parameter PopulateFlowActions is set, this means the flow is not being
        // called to further the flow, but rather from OpenAndLockWork or bulk processing to get the flow actions
        String populateFlowActions = tools.getParamValue("PopulateFlowActions");
        if (populateFlowActions != null && populateFlowActions.equals("true")) {
            String strAssignmentPageName = tools
                    .getParamValue("AssignmentPageName");
            PopulateFlowActions_circum0(pyLastFlowStep, strAssignmentPageName);
            return;
        }

        // If the parameter TicketRaised is set, it means this flow is being called by
        // FlowTicketEvaluate, which itself is called by Obj-Set-Tickets
        String strTicketRaised = tools.getParamValue("TicketRaised");
        if (strTicketRaised != null && strTicketRaised.equalsIgnoreCase("true")) {
            ClipboardProperty cpTickets = workPage.getProperty(".pxTickets");
            // Loop through the tickets for tracing purposes
            java.util.Iterator tix = cpTickets.iterator();
            while (tix.hasNext()) {
                ClipboardProperty ticket = (ClipboardProperty) tix.next();
                LogTraceEvent(mRuleSet, ticket.getStringValue(), flowName,
                        "Flow Ticket Raised", "", mFlowHandle, "");
            }

            boolean resumingFlowViaTicket = false;
            boolean bHasTicket = false;

            // If no (new) tickets found for this flow, abort
            if (!resumingFlowViaTicket)
                return;
        } else if (pyNextFlowStep.length() > 0) {
            // The flow is not being called for ticket processing, yet pyNextFlowStep is set.
            // This means the flow is jumping back to a previous step; clean up the previous step
            if (pyLastFlowStep.length() == 0) {
                // We actually haven't started the flow and are 'jumping' to an alternate start shape
                currentFlow
                        .putString(".pxTimeFlowStarted", pega_rules_datetime
                                .pxGetCurrentTimeStampThreadUnique());
                currentFlow.putString(".pxFlowInsKey", mFlowHandle);
                currentFlow.getProperty(".pyDraftMode").setValue(bDraftMode);
                currentFlow.putString(".pyCategory", "ScreenFlow");
                currentFlow.putString(".pyDeferCommit", "false");
                currentFlow.putString(".pyDeferErrors", "false");
                // even though this is the flow's first run, the purpose of the flag is to determine
                // whether the parent flow waiting on it should be restarted or not.  In this case the parent is not waiting
                // since we are jumping in via ticket so if the flow ends it must be restarted.
                currentFlow.getProperty(".pyFirstRun").setValue(false);
            } else if (!pyLastFlowStep.equals(pyNextFlowStep)) {
                // We are switching to a different step; clean the previous one up completely
                pega_processengine_flowutilities.RemoveAssignmentByTicket(
                        tools, currentFlow, "FLOW JUMPBACK");
                pega_procom_flow.CleanUpSplit(workPage, currentFlow, tools);
            } else {
                // We are returning to the same step.  If there isn't a jump subscript, we are restarting it
                String jumpToSubscript = currentFlow
                        .getString(".pyJumpToSubscript");
                if (jumpToSubscript.length() > 0) {
                    pega_processengine_flowutilities.RemoveAssignmentByTicket(
                            tools, currentFlow, "FLOW JUMPBACK");

                    // Remove any other flows that were split off (so we can start fresh)
                    ClipboardProperty pxFlow = workPage.getProperty(".pxFlow");
                    Iterator flows = pxFlow.iterator();
                    while (flows.hasNext()) {
                        ClipboardProperty thisFlow = (ClipboardProperty) flows
                                .next();
                        if (thisFlow.getStringValue(".pyIssuedFromFlow")
                                .equals(currentFlow.getString(".pxSubscript"))) {
                            pega_procom_flow.CleanUpSplit(workPage,
                                    thisFlow.getPageValue(), tools);
                            pega_procom_flow.RemoveFlow(workPage,
                                    thisFlow.getName(), tools);
                        }
                    }
                } else {
                    pega_processengine_flowutilities.RemoveAssignmentByTicket(
                            tools, currentFlow, "FLOW JUMPBACK");
                    pega_procom_flow.CleanUpSplit(workPage, currentFlow, tools);
                    // Additionally, we need to remove all the completed flow information
                    // since we've already wiped out the split tracking properties
                    ClipboardProperty taskPath = currentFlow
                            .getProperty(".pyFlowPath");
                    java.util.Iterator taskIt = taskPath.iterator();
                    while (taskIt.hasNext()) {
                        ClipboardProperty taskPageRef = (ClipboardProperty) taskIt
                                .next();
                        ClipboardPage taskPage = taskPageRef.getPageValue();
                        String strHistoryTaskName = taskPage
                                .getString(".pyFlowStep");
                        if (strHistoryTaskName.equals(pyNextFlowStep)) {
                            taskPage.getProperty(".pyCompletedSubFlowPath")
                                    .removeFromClipboard();
                            taskPage.getProperty(".pyCompletedSplitForEachPath")
                                    .removeFromClipboard();
                            break;
                        }
                    }
                }
            }
        }

        // Make sure we haven't been called more than 500 times
        ClipboardProperty pyFlowCalledCount = currentFlow
                .getProperty(".pyFlowCalledCount");
        if (!pyFlowCalledCount.hasGoodValue()) {
            pyFlowCalledCount.setValue(1);
        } else {
            int timesCalled = pyFlowCalledCount.toInteger();
            if (timesCalled > 500) {
                // we're probably in an infinite loop, maybe through tickets
                String errMessage = "ProblemFlow_InfiniteLoop";
                if (pyNextFlowStep.length() > 0) {
                    errMessage += "NextStepInfo\t" + pyNextFlowStep;
                }
                ProblemFlow(errMessage, pyLastFlowStep);
                return;
            } else {
                pyFlowCalledCount.setValue(++timesCalled);
            }
        }

        // Clear out old assignment information
        ClipboardProperty assignmentKey = currentFlow
                .getProperty(".pxAssignmentKey");
        if (!assignmentKey.getStringValue().equals(""))
            currentFlow.getProperty(".pyFlowPath(<LAST>).pyRouteTo").setValue(
                    tools.findPage("pxRequestor")
                            .getString(".pyUserIdentifier"));
        assignmentKey.removeFromClipboard();
        currentFlow.remove(".pxAssignActivity");
        currentFlow.remove(".pxAssignClass");
        currentFlow.remove(".pxAssignDeadTime");
        currentFlow.remove(".pxAssignGoalTime");
        currentFlow.remove(".pxAssignSvcLevel");
        currentFlow.remove(".pxAssignStatus");
        currentFlow.remove(".pyInstructions");
        currentFlow.remove(".pyConfirmationNote");
        currentFlow.remove(".pxRouteTo");
        currentFlow.remove(".pxRouteToUserName");
        currentFlow.remove(".pxAssignCanPerform");
        currentFlow.remove(".pxAssignIsVirtual");

        // Record the current status from the clipboard.  This is used
        // to determine the flow action picked (if applicable)
        currentFlow.putString(".pxLastActivityStatus",
                tools.findPage("pxThread").getString(".pxTaskStatus"));

        // Increment flow count
        com.pega.pegarules.pub.pal.PAL pal = tools.getPAL();
        pal.incrementFlowCount();

        // Time to start flow processing!
        String sFlowDebuggerNextAction = tools.getParamValue("stepType");
        nextFlowStepToRun = "";
        if (pyLastFlowStep.length() == 0 && pyNextFlowStep.length() == 0) {
            // First run of this flow
            if (!newObject) {
                if (pyFlowParametersPage != null
                        && !pyFlowParametersPage.isEmpty()) {
                    pega.setParameterPage(pyFlowParametersPage);
                }
                // Non starting flows must have all the required parameters present.
                boolean bAddedMessage = false;
                if (bAddedMessage)
                    return;
            }

            // if pyCurrentFlowStep isn't blank, it means we are coming back in from a problem flow
            String pyCurrentFlowStep = currentFlow.getString(
                    ".pyCurrentFlowStep").trim();
            if (pyCurrentFlowStep.equals("")) {
                currentFlow.getProperty(".pyFirstRun").setValue(true);
                currentFlow
                        .putString(".pxTimeFlowStarted", pega_rules_datetime
                                .pxGetCurrentTimeStampThreadUnique());
                currentFlow.putString(".pyFlowInterestPageClass", tools
                        .getPrimaryPage().getClassName());
                currentFlow.putString(".pyCategory", "ScreenFlow");
                currentFlow.putString(".pyDeferCommit", "false");
                currentFlow.getProperty(".pyDraftMode").setValue(bDraftMode);
                currentFlow.putString(".pyDeferErrors", "false");
                currentFlow.getProperty(".pxSystemFlow").setValue("false");
                currentFlow.putString(".pxFlowInsKey", mFlowHandle);
                currentFlow.putString(".pyFlowTypeLabel", "Create");

                // Copy all the parameters from the parameter page into the flow parameter page
                pyFlowParametersPage.copyParameterValues((ParameterPage) tools
                        .getStackFrame().getParameterPage());
                pega.setParameterPage(pyFlowParametersPage);
            }

            LogTraceEvent(mRuleSet, mFlowHandle, flowName, "Flow Start", "",
                    mFlowHandle, "");

            // Run the starting task of the flow
            Task_Start62_circum0();
            // Expression: 
            String stepSLAParam = tools.getParamValue("StepSLA"); //passed in as a stage step SLA from the case type
            String slaToUse = "";
            if (!"".equals(stepSLAParam)) {
                slaToUse = stepSLAParam;
                tools.putParamValue("StepSLA", "");
            } else {
                slaToUse = "";
            }
            if (!"".equals(slaToUse)) {
                // Open the service level rule here, where the interest page is the primary page
                // so that circumstances work correctly
                ClipboardPage sla = tools.createPage("Rule-Obj-ServiceLevel",
                        "");
                sla.putString(".pyClassName", tools.getPrimaryPage()
                        .getClassName());
                sla.putString(".pyServiceLevelName", slaToUse);
                try {
                    sla = tools.getDatabase().open(sla, true);
                } catch (DatabaseException e) {
                    ProblemFlow(e.getMessage(), "");
                    return;
                }
                if (sla == null) {
                    ProblemFlow("ProblemFlow_ServiceLevelNotFound\t"
                            + tools.getPrimaryPage().getClassName() + "."
                            + slaToUse, "");
                }
                //Launch companion for this flow's SLA
                String companionFlowName = launchCompanionFlow(workPage,
                        interestPage.getName(), slaToUse);
                //keep track of the name of the companion flow in pxFlow
                currentFlow.putString("pyCompanionFlow", companionFlowName);
                currentFlow.putString("pyFlowSLA", slaToUse);
            }
        } else if (pyLastFlowStep.length() > 0 && pyNextFlowStep.length() == 0) {
            // The flow has been resumed, and not by a ticket
            pega.setParameterPage(pyFlowParametersPage);
            currentFlow.putString(".pyDeferCommit", "false");
            LogTraceEvent(mRuleSet, mFlowHandle, flowName, "Flow Resumed", "",
                    mFlowHandle, "");

            if (pyLastFlowStep.equals("AssignmentSF4"))
                TaskConnectors_AssignmentSF4_circum0();
            else if (pyLastFlowStep.equals("Decision1"))
                TaskConnectors_Decision1_circum0();
            else if (pyLastFlowStep.equals("SubProcessSF1"))
                TaskConnectors_SubProcessSF1_circum0();
            else if (pyLastFlowStep.equals("Start62"))
                TaskConnectors_Start62_circum0();
            else if (pyLastFlowStep.equals("AssignmentSF1"))
                TaskConnectors_AssignmentSF1_circum0();
            else if (pyLastFlowStep.equals("AssignmentSF2"))
                TaskConnectors_AssignmentSF2_circum0();
            else if (pyLastFlowStep.equals("AssignmentSF3"))
                TaskConnectors_AssignmentSF3_circum0();
            else
                // The specified step has been removed; run the starting task of the flow instead
                Task_Start62_circum0();

            // If we are using the flow debugger, set pyNextFloWStep to nextFlowStepToRun, blank pyLastFlowStep and exit.
            if (pega_rules_settings.IsFlowDebuggerEnabled()
                    && !sFlowDebuggerNextAction.equals("NextAssignment")
                    && !sFlowDebuggerNextAction.equals("NextShape")) {
                oLog.infoForced("casad: flowmethodbody1132; flow debugger in use!.");
                pyLastFlowStep = "";
                currentFlow.putString(".pyLastFlowStep", "");
                currentFlow.putString(".pyNextFlowStep", nextFlowStepToRun);
                return;
            }

        }

        else {
            // pyNextFlowStep has value, so we were either called with a ticket
            // or resumed by a jump back to earlier step
            currentFlow.putString(".pyDeferCommit", "false");
            currentFlow.putString(".pyDeferErrors", "false");
            pega.setParameterPage(pyFlowParametersPage);
            LogTraceEvent(mRuleSet, mFlowHandle, flowName,
                    "Flow Resumed Via Ticket", "", mFlowHandle, "");

            nextFlowStepToRun = pyNextFlowStep;
        }

        // Now loop through our state machine until we idle.
        // At the start of every task, we blank out nextFlowStepToRun so that
        // if the flow ends, idles, or runs into an issue, we'll exit the loop.
        while (!nextFlowStepToRun.equals("")) {
            // Re-read the pyTemporaryObject flag, in case it changed
            isTemporaryObject = workPage.getProperty(".pyTemporaryObject")
                    .toBoolean();

            if (nextFlowStepToRun.equals("AssignmentSF4"))
                Task_AssignmentSF4_circum0();
            else if (nextFlowStepToRun.equals("Decision1"))
                Task_Decision1_circum0();
            else if (nextFlowStepToRun.equals("END66"))
                Task_END66_circum0();
            else if (nextFlowStepToRun.equals("SubProcessSF1"))
                Task_SubProcessSF1_circum0();
            else if (nextFlowStepToRun.equals("Start62"))
                Task_Start62_circum0();
            else if (nextFlowStepToRun.equals("AssignmentSF1"))
                Task_AssignmentSF1_circum0();
            else if (nextFlowStepToRun.equals("AssignmentSF2"))
                Task_AssignmentSF2_circum0();
            else if (nextFlowStepToRun.equals("AssignmentSF3"))
                Task_AssignmentSF3_circum0();
            else if (buildFlowPage) {
                // The specified subprocess whose flow page needs to be built is missing
                workPage.addMessage("pyFlow_StepNotInTheFlow\t"
                        + nextFlowStepToRun + "\t"
                        + currentFlow.getString(".pyFlowType"));
                return;
            } else {
                nextFlowStepToRun = "";
                // The specified step has been removed; run the starting task of the flow instead
                Task_Start62_circum0();
            }
            // Pause for the flow debugger
            boolean bInFlowTracer = true;
            if (pega_rules_settings.IsFlowDebuggerEnabled()
                    && !sFlowDebuggerNextAction.equals("NextAssignment")) { // Check to see if the flow debugger is enabled
                oLog.infoForced("casad: flowmethodbody1259; flow debugger in use!.");
                nextFlowStepToRun = "";
                pyNextFlowStep = "";
                currentFlow.putString(".pyNextFlowStep", "");
            }
        }

        // Set pyNextFlowStep to blank now that processing has ended/idled
        pyNextFlowStep = "";
        if (currentFlow != null && !currentFlow.isEmpty()) {
            currentFlow.putString(".pyNextFlowStep", "");
            currentFlow.putString(".pyJumpToSubscript", "");
        }

        boolean bNeedToSaveWorkObject = true;
        String pyIssuedFromObject = "";
        String pyIssuedFromFlow = "";
        boolean firstRun = false;
        if (currentFlow != null && !currentFlow.isEmpty()) {
            pyIssuedFromObject = currentFlow.getString(".pyIssuedFromObject");
            pyIssuedFromFlow = currentFlow.getString(".pyIssuedFromFlow");
            firstRun = currentFlow.getProperty(".pyFirstRun").toBoolean();
        }

        if (pyIssuedFromFlow.equals("") && newObject) {
            callerParameterPage.putString("pyNextAssignment", "true");
            callerParameterPage
                    .putString("pyNextAssignmentWorkBasket", "false");
            callerParameterPage.putString("pyConfirmChoice", "Harness");
            callerParameterPage.putString("pyConfirmHarness", "Confirm");
        }

        callerParameterPage.putString("pyNextAssignmentAdhoc", "true");
        callerParameterPage.putString("pyNextAssignmentWorkBasketAdhoc",
                "false");
        if (newObject && currentFlow != null) {
            // Re-read pyNewObject flag from the flow in case processing found a duplicate in the system and just re-read it
            ClipboardProperty pyNewObjectParam = currentFlow
                    .getIfPresent(".pyFlowParameters.pyNewObject");
            if (pyNewObjectParam != null)
                newObject = pyNewObjectParam.toBoolean();
        }
        if (CheckIfFlowHasEnded()) {
            LogTraceEvent(mRuleSet, mFlowHandle, flowName, "Flow End", "",
                    mFlowHandle, "");

            if (currentFlow != null && !currentFlow.isEmpty()) {
                String pyIssuedAs = currentFlow.getString(".pyIssuedAs");
                String pyIssuedFromTask = currentFlow
                        .getString(".pyIssuedFromTask");
                String pyIssuedFromPage = currentFlow
                        .getString(".pyIssuedFromPage");
                if (workPage.getProperty(".pxMarkedForSuspension").toBoolean() == true) {
                    // The work object is being suspended.  We only remove the flow page if there
                    // is an issuing flow (which we will let run to its own assignment or end).
                    if (!pyIssuedFromFlow.equals("")
                            || currentFlow.getProperty("pxIsInvestigative")
                                    .toBoolean() == true) {
                        pega_procom_flow.RemoveFlow(workPage, flowName, tools);
                        currentFlow = null;
                        if (firstRun == false)
                            RestartIssuingFlow(pyIssuedFromObject,
                                    pyIssuedFromFlow, pyIssuedAs,
                                    pyIssuedFromTask, pyIssuedFromPage);
                    } else
                        currentFlow.putString(".pxSuspendedType", "FLOWEND");
                } else {
                    // First let's grab all the factors that will influence what we should do next
                    boolean bRemoveFlow = false;
                    boolean bWorkPageHasMessages = workPage.hasMessages();
                    boolean bCurrentFlowDefersCommit = currentFlow.getProperty(
                            ".pyDeferCommit").toBoolean();
                    boolean bCurrentFlowDefersErrors = currentFlow.getProperty(
                            ".pyDeferErrors").toBoolean();
                    boolean bHasParentFlow = !pyIssuedFromFlow.equals("");
                    boolean bParentFlowDefersErrors = false;
                    if (bHasParentFlow
                            && pyIssuedFromObject.equals(workPageHandle)) {
                        bNeedToSaveWorkObject = false;
                        ClipboardProperty parentFlowRef = workPage
                                .getIfPresent(".pxFlow(" + pyIssuedFromFlow
                                        + ")");
                        if (parentFlowRef != null) {
                            ClipboardPage parentPage = parentFlowRef
                                    .getPageValue();
                            bParentFlowDefersErrors = parentPage.getProperty(
                                    ".pyDeferErrors").toBoolean();
                        }
                    }

                    if (!bCurrentFlowDefersCommit)
                        // If we aren't in defer commit mode we can safely remove the flow page
                        bRemoveFlow = true;
                    else if (!bWorkPageHasMessages)
                        // If there aren't any errors, even in defer commit mode it is safe to remove the flow page
                        bRemoveFlow = true;
                    else if (bCurrentFlowDefersErrors
                            && bParentFlowDefersErrors)
                        // We can remove the flow page because the flow path is copied to the parent flow and the
                        // Assign-.RestoreAssignment will rebuild the flow page from the flow path if necessary.
                        bRemoveFlow = true;
                    else {
                        // We can't remove the flow page because either it or its parent doesn't defer errors.  If it
                        // doesn't have a parent, then Assign-.RestoreAssignment won't work correctly if we did remove it.
                        bRemoveFlow = false;
                    }

                    if (bRemoveFlow) {
                        pega_procom_flow.RemoveFlow(workPage, flowName, tools);

                        // code to call onprocessEnd purpose in the case type
                        String strIssuedFromStage = currentFlow
                                .getString(".pyIssuedFromStage");
                        if (!strIssuedFromStage.equals("")) {
                            StringMap keys = new HashStringMap();
                            keys.putString("pxObjClass", "Rule-Obj-Activity");
                            keys.putString("pyClassName", "Work-");
                            keys.putString("pyActivityName", "pzOnProcessEnd");
                            ParameterPage ParamsPg = new ParameterPage();
                            ParamsPg.putString("pyCaseTypePurpose",
                                    "STAGE_ONPROCESSEND");
                            ParamsPg.putString("CurrentStage",
                                    currentFlow.getString(".pyIssuedFromStage"));
                            ParamsPg.putString("CurrentProcess",
                                    "CreateForm_Default");
                            ParamsPg.putString("CurrentflowName",
                                    tools.getParamValue("flowName"));
                            ParamsPg.putString("firstRun",
                                    currentFlow.getString(".pyFirstRun"));
                            ParamsPg.putString(
                                    "CurrentStageSubscript",
                                    currentFlow
                                            .getString(".pyIssuedFromStageSubscript"));
                            ParamsPg.putString("CurrentStageLabel", currentFlow
                                    .getString(".pyIssuedFromStageLabel"));
                            ParamsPg.putString("StageFlowID",
                                    currentFlow.getString(".pxStageFlowID"));
                            try {
                                tools.doActivity(keys, workPage, ParamsPg);
                            } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                                tools.getStepStatus().backoutWorst();
                            }
                        }
                        currentFlow = null;
                        if (firstRun == false && bHasParentFlow)
                            if (!RestartIssuingFlow(pyIssuedFromObject,
                                    pyIssuedFromFlow, pyIssuedAs,
                                    pyIssuedFromTask, pyIssuedFromPage))
                                bNeedToSaveWorkObject = true;
                    } else
                        bNeedToSaveWorkObject = true;
                }
            }

            callerParameterPage.putString("FlowHasEnded", "Y");
        } else {
            // The flow is just idle
            if (firstRun && !pyIssuedFromFlow.equals("")
                    && pyIssuedFromObject.equals(workPageHandle))
                // the parent flow that just spawned this will do a save of the work object
                bNeedToSaveWorkObject = false;
            currentFlow.getProperty(".pyFirstRun").setValue(false);
            currentFlow.getProperty(".pxLastUpdateBy").setValue(
                    tools.findPage("pxRequestor")
                            .getString(".pyUserIdentifier"));
            callerParameterPage.putString("FlowHasEnded", "N");
            // Let's reset the flow called count since we've idled
            currentFlow.getProperty(".pyFlowCalledCount").setValue(1);
            // Let's reset the flag for returning to the previous operator
            currentFlow.remove(".pyUsePreviousOperator");
        }

        if (workPageCorrupted) {
            bNeedToSaveWorkObject = true;
            LogTraceMessage("Warning: " + workPageHandle
                    + " is lost and cannot be saved");
        }
        if (workPage.getBoolean("pzStageStartInProgress")) {
            bNeedToSaveWorkObject = false;
        }
        if (!(workPage.getProperty(".pyTemporaryObject").toBoolean())
                && bNeedToSaveWorkObject) {
            if (currentFlow == null
                    || currentFlow.getProperty(".pyDeferErrors").toBoolean() == false
                    || !workPage.hasMessages()) {
                // Finally, save the work object
                ParameterPage tempPP = new ParameterPage();
                if (newObject)
                    tempPP.putString("newObject", "true");
                RunTaskActivity("", "RecalculateAndSave", workPage, tempPP);
            }
        }

    } // end of perform definition

    public void fuaInit() {
    }

    public void fuaDestroy() {
    }

    private static final String oDependencies[] = {
            "Rule-Obj-Activity:DOFUASSEMBLY",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODFINISH",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODHEADER",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODVERSIONHEADER",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODCLOSE",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODBODY",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODABSENT",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODCALLVERSION",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODINIT",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODCALL",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODACCESS",
            "Rule-Utility-Function:FLOWFUA!FLOWMETHODVERSIONFINISH" };

    public String[] getUsedRules() {
        return oDependencies;
    }

    private static final DependentRuleInfo[] oDependentRuleInfos = {
            new DependentRuleInfo("DOFUASSEMBLY", "Rule-Obj-Activity", "",
                    true, "Rule-Obj-Flow", "", "", "", "!DOFUASSEMBLY", true,
                    false, "ABSOLUTE_IS_NOT_PRIMARY", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODFINISH",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-02-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODFINISH #20181025T195011.922 GMT",
                    "FLOWFUA!FLOWMETHODFINISH", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODHEADER",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODHEADER #20180713T133137.690 GMT",
                    "FLOWFUA!FLOWMETHODHEADER", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODVERSIONHEADER",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODVERSIONHEADER #20180713T133137.724 GMT",
                    "FLOWFUA!FLOWMETHODVERSIONHEADER", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODCLOSE",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCLOSE #20180713T133137.681 GMT",
                    "FLOWFUA!FLOWMETHODCLOSE", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODBODY",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-05-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODBODY #20200408T115536.470 GMT",
                    "FLOWFUA!FLOWMETHODBODY", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODABSENT",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODABSENT #20180713T133137.655 GMT",
                    "FLOWFUA!FLOWMETHODABSENT", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODCALLVERSION",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCALLVERSION #20180713T133137.676 GMT",
                    "FLOWFUA!FLOWMETHODCALLVERSION", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODINIT",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODINIT #20180713T133137.711 GMT",
                    "FLOWFUA!FLOWMETHODINIT", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODCALL",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCALL #20180713T133137.671 GMT",
                    "FLOWFUA!FLOWMETHODCALL", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODACCESS",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODACCESS #20180713T133137.659 GMT",
                    "FLOWFUA!FLOWMETHODACCESS", true, false,
                    "ABSOLUTE_CLASSLESS", 1),
            new DependentRuleInfo(
                    "FLOWFUA!FLOWMETHODVERSIONFINISH",
                    "Rule-Utility-Function",
                    "",
                    false,
                    "",
                    "Pega-ProcessEngine",
                    "08-01-01",
                    "RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODVERSIONFINISH #20180713T133137.719 GMT",
                    "FLOWFUA!FLOWMETHODVERSIONFINISH", true, false,
                    "ABSOLUTE_CLASSLESS", 1) };

    /**
    * This method returns the set of dependent rules required to build
    * the generated code and (possibly) whole classes that the rules assembler declared.
    * @return array of <code>DependentRuleInfo</code> objects.
    */
    public DependentRuleInfo[] getDependentRuleInfos() {
        return oDependentRuleInfos;
    }

    //	Rules used in this assembly (order and duplicates ARE significant to hash code) :
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODABSENT #20180713T133137.655 GMT:20180713T133137.655 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODACCESS #20180713T133137.659 GMT:20180713T133137.659 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODBODY #20200408T115536.470 GMT:20200625T072258.118 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCALL #20180713T133137.671 GMT:20180713T133137.671 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODFINISH #20181025T195011.922 GMT:20181119T203009.830 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODHEADER #20180713T133137.690 GMT:20180713T133137.690 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODVERSIONHEADER #20180713T133137.724 GMT:20180713T133137.724 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCALLVERSION #20180713T133137.676 GMT:20180713T133137.676 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODVERSIONFINISH #20180713T133137.719 GMT:20180713T133137.719 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODINIT #20180713T133137.711 GMT:20180713T133137.711 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCLOSE #20180713T133137.681 GMT:20180713T133137.681 GMT

    //	Functions used during this assembly :
    //	RULE-UTILITY-FUNCTION DECISIONTABLE OBTAINVALUE--(PUBLICAPI,CLIPBOARDPAGE,STRING,BOOLEAN) #20200303T235821.399 GMT:20200303T235821.399 GMT
    //	RULE-UTILITY-FUNCTION DEFAULT QUOTE #20180713T131438.272 GMT:20180713T131438.272 GMT
    //	RULE-UTILITY-FUNCTION DEFAULT UNQUOTE #20180713T131438.311 GMT:20180713T131438.311 GMT
    //	RULE-UTILITY-FUNCTION FLOW GETSORTEDCONNECTORS #20180713T132449.426 GMT:20180713T132449.426 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA ADDFLOWACTION #20180713T133137.599 GMT:20180713T133137.599 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA ADDTASKCOST #20180713T133137.602 GMT:20180713T133137.602 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA ADDTASKTOFLOWPATH #20180713T133137.606 GMT:20180713T133137.606 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA AREALLOTHERINCOMINGCONNECTORSACCOUNTEDFOR #20180713T133137.610 GMT:20180713T133137.610 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA BUILDNAVIGATIONPATH #20180713T133137.615 GMT:20180713T133137.615 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA CHECKFLOWSECURITY #20180713T133137.619 GMT:20180713T133137.619 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA CHECKIFFLOWHASENDED #20180713T133137.624 GMT:20180713T133137.624 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA CONNECTORMETHOD #20181116T124640.322 GMT:20181127T084242.257 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA CREATENEWPARAMSPAGE #20180713T133137.637 GMT:20180713T133137.637 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA EVALUATECONNECTOR #20180713T133137.644 GMT:20180713T133137.644 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWACTIONMETHODS #20200528T084214.982 GMT:20200528T084214.982 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODBODY #20200408T115536.470 GMT:20200625T072258.118 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODCLOSE #20180713T133137.681 GMT:20180713T133137.681 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODFINISH #20181025T195011.922 GMT:20181119T203009.830 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODHEADER #20180713T133137.690 GMT:20180713T133137.690 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA FLOWMETHODINIT #20180713T133137.711 GMT:20180713T133137.711 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA GETCOORDINATES #20180713T133137.728 GMT:20180713T133137.728 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA GETNAVIGATIONPATH #20180713T133137.739 GMT:20180713T133137.739 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA GETTASKTYPE #20180713T132449.218 GMT:20180713T132449.218 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA GETTASKTYPE #20180713T133137.743 GMT:20180713T133137.743 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA LAUNCHFLOW #20181004T065745.368 GMT:20181004T065745.368 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA LOGTRACEEVENT #20180713T133137.753 GMT:20180713T133137.753 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PASSPARAMETERS #20180713T133137.764 GMT:20180713T133137.764 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA POPULATEFLOWACTIONS #20200302T111104.178 GMT:20200522T155441.225 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PROBLEMFLOW #20181119T203009.733 GMT:20181119T203009.733 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PROCESSCONNECTOR #20190509T123135.762 GMT:20190509T123135.762 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PZGETORDEREDSTEPS--(PUBLICAPI,CLIPBOARDPAGE) #20180713T133137.791 GMT:20180713T133137.791 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PZLAUNCHCOMPANIONFLOW #20180713T133137.798 GMT:20180713T133137.798 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PZPASSPARAMETERS--(COM.PEGA.PEGAD49E0D8BA7DC1B37CE1D1C16C838C975 #20180713T133137.804 GMT:20180713T133137.804 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA PZPOPULATECASETYPEFLOWACTIONS #20180713T133137.809 GMT:20180713T133137.809 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA RESTARTISSUINGFLOW #20180713T133137.813 GMT:20180713T133137.813 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA RUNTASKACTIVITY #20190506T123046.152 GMT:20190520T163429.422 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA STUCKFLOW #20180713T133137.825 GMT:20180713T133137.825 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASKENDSETTINGS #20181025T195011.866 GMT:20181025T195011.866 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASKHISTORY #20181025T195011.890 GMT:20181025T195011.890 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASKMETHOD #20180713T133139.012 GMT:20180713T133139.012 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASKMETHODHEADER #20180713T133139.015 GMT:20180713T133139.015 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_ASSIGNMENT #20200428T133800.554 GMT:20200428T133800.554 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_DECISION #20180713T133137.839 GMT:20180713T133137.839 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_FLOW #20180713T133137.845 GMT:20180713T133137.845 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_FLOWEND #20180713T133137.851 GMT:20180713T133137.851 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_FLOWSTART #20190628T101300.717 GMT:20190731T090756.707 GMT
    //	RULE-UTILITY-FUNCTION FLOWFUA TASK_FORK #20180713T133139.003 GMT:20180713T133139.003 GMT
    //	RULE-UTILITY-FUNCTION FLOWUTILITIES PZGETDISPLAYABLEINCOMINGSHAPEIDS--(CLIPBOARDPAGE,STRING) #20180713T133139.140 GMT:20180713T133139.140 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETCACHEDDATA #20180713T131743.589 GMT:20180713T131743.589 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETCACHEDDATA--(STRING,STRING,BO59DE8A46A59D8070C4228B2B318D4E85 #20180713T131743.593 GMT:20180713T131743.593 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETDEFAULTMAX #20180713T131743.602 GMT:20180713T131743.602 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETDIRECTORYNAME #20180713T131743.606 GMT:20180713T131743.606 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETDIRECTORYNAME--(STRING,STRING,STRING,STRING,STRING,PRTHREAD) #20180713T131743.611 GMT:20180713T131743.611 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETFILENAME #20180713T131743.614 GMT:20180713T131743.614 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETLOCKSTRING #20180713T131743.621 GMT:20180713T131743.621 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST GETLOOKUPLISTDIRPATH #20180713T131743.625 GMT:20180713T131743.625 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST PROCESS--(STRING,STRING,STRING,SB3377D7EBCA0E461918F1C703F4F7202 #20180713T132723.315 GMT:20180713T132723.315 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST READFILEASPAGE #20180713T131743.663 GMT:20180713T131743.663 GMT
    //	RULE-UTILITY-FUNCTION LOOKUPLIST WRITEINFO #20180713T131743.672 GMT:20180713T131743.672 GMT
    //	RULE-UTILITY-FUNCTION PEGARULESUTILITIES CREATEREFERENCESFORPARAMETERS #20180713T131441.399 GMT:20180713T131441.399 GMT
    //	RULE-UTILITY-FUNCTION PEGARULESUTILITIES CREATEREFERENCESFORPARAMETERS--(9CE1EB0AC70676F8A514EA00342440FC #20180713T131441.407 GMT:20180713T131441.407 GMT
    //	RULE-UTILITY-FUNCTION PEGARULESUTILITIES CREATEREFERENCESFORPARAMETERS2 #20180713T131441.403 GMT:20180713T131441.403 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT ADDSHAPETOPATH #20180713T141333.040 GMT:20180713T141333.040 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT BUILDMOSTLIKELYPATH #20180713T141333.043 GMT:20180713T141333.043 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT BUILDREMAININGPATH #20180713T141333.046 GMT:20180713T141333.046 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT GETSORTEDCONNECTORSFORDOC #20180713T141333.056 GMT:20180713T141333.056 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT PZBUILDUNATTACHEDPATH #20180713T141333.135 GMT:20180713T141333.135 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT PZGETATTACHEDTOSHAPES #20180713T141333.195 GMT:20180713T141333.195 GMT
    //	RULE-UTILITY-FUNCTION PZPROCESSARCHITECT PZGETORDEREDSHAPEINFO_DETAILS #20180713T141333.236 GMT:20180713T141333.236 GMT
    //	RULE-UTILITY-FUNCTION STRING ISLITERAL #20180713T131441.656 GMT:20180713T131441.656 GMT
    //	RULE-UTILITY-FUNCTION STRING REPLACEALL #20180713T131442.783 GMT:20180713T131442.783 GMT
    //	RULE-UTILITY-FUNCTION STRING WHATCOMESBEFORELAST #20180713T131442.860 GMT:20180713T131442.860 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES CALLWHEN #20180713T131442.964 GMT:20180713T131442.964 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES ESCAPESPECIALCHARSANDQUOTE #20180713T131443.047 GMT:20180713T131443.047 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES GETPUBLICAPI #20180713T131444.323 GMT:20180713T131444.323 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES GETRULESYSTEMSETTING #20180713T131444.340 GMT:20180713T131444.340 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES ISFIELDVALUE #20180713T131444.390 GMT:20180713T131444.390 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES ISINPAGELIST #20180713T131444.398 GMT:20180713T131444.398 GMT
    //	RULE-UTILITY-FUNCTION UTILITIES ISORINHERITSFROM #20180713T131444.415 GMT:20180713T131444.415 GMT

    /**
     * provides a stand-in for uniqueness tests for the
     * content of this stream. Created from list of rules
     * and dates listed in the comment above this declaration.
     * @return	32 byte hash of that list using MD5
     */
    public static String getFUAContentHash() {
        return "82948fb729a00b9019fa1800eb4d715f";
    }

    public String getDefinitionAppliesToClass() {
        return "OOO-PGB-Work-Demo";
    }

    public String getAspect() {
        return "Action";
    }

    private boolean[] TaskLocalActions_AssignmentSF4_circum0(
            ClipboardPage assignPage, boolean isFirstAction,
            boolean isFirstLocalAction) {
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    private void Task_AssignmentSF4_circum0() {
        String taskName = "AssignmentSF4";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "AssignmentSF4");
        currentFlow.putString(".pyCurrentFlowStepLabel", "Step D");
        String taskActivity = "WorkList";

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        // Create a temporary assignment page for the routing activity to access
        ClipboardPage pg_tempAssignPage = tools.createPage("Assign-",
                "AssignDefaults");
        newParamsPage.putString("AssignPage", "AssignDefaults");

        String routeTo = "";
        ClipboardProperty cpUsePreviousOperator = currentFlow
                .getIfPresent("pyUsePreviousOperator");
        if (cpUsePreviousOperator != null
                && cpUsePreviousOperator.toBoolean() == true) {
            // See if a previous operator can be located in the breadcrumb trail.
            ClipboardProperty taskPath = currentFlow.getProperty(".pyFlowPath");
            java.util.Iterator taskIt = taskPath.iterator();
            while (taskIt.hasNext()) {
                ClipboardProperty taskPageRef = (ClipboardProperty) taskIt
                        .next();
                ClipboardPage taskPage = taskPageRef.getPageValue();
                String strHistoryTaskName = taskPage.getString(".pyFlowStep");
                if (strHistoryTaskName.equals(taskName)) {
                    routeTo = taskPage.getString("pyRouteTo");
                    // Make sure the routing operator is available
                    if (routeTo.length() > 0)
                        routeTo = pega_procom_routing.checkForSubstitute(
                                routeTo, workPage, pg_tempAssignPage, tools);
                    break;
                }
            }
        }

        // Flow Route Start tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route Start", "", mFlowHandle, taskName);

        String defaultAssignTo = pyFlowParametersPage
                .getString("DefaultAssignTo");
        if (defaultAssignTo != null && defaultAssignTo.length() > 0)
            newParamsPage.putString("AssignTo", defaultAssignTo);
        else {
            if (!RunTaskActivity(taskName, "ToCurrentOperator", workPage,
                    newParamsPage))
                return;
        }

        if (pg_tempAssignPage.getString(".pxAssignedOperatorID").length() > 0) {
            newParamsPage.putString("AssignTo",
                    pg_tempAssignPage.getString(".pxAssignedOperatorID"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else if (newParamsPage.getString("AssignTo").length() > 0) {
            pg_tempAssignPage.putString(".pxAssignedOperatorID",
                    newParamsPage.getString("AssignTo"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else {
            pg_tempAssignPage.removeFromClipboard();

            // Check if we should short-circuit this assignment
            String passThrough = pega_rules_default
                    .getCheckBoxBoolean(newParamsPage
                            .getString("PassThroughAssignment"));
            if (passThrough.equals("true")) {
                String actionToTake = newParamsPage
                        .getString("FlowActionToTake");
                if (actionToTake == null || actionToTake.equals("")) {
                    actionToTake = "ActionStub";
                }
                currentFlow.putString(".pxLastActivityStatus", actionToTake);
                tools.findPage("pxThread").putString(".pxTaskStatus",
                        actionToTake);
                currentFlow.putString(".pyLastFlowStep", taskName);

                // Flow Route End tracer message
                LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                        "Flow Route End", "Assignment Skipped", mFlowHandle,
                        taskName);

                TaskConnectors_AssignmentSF4_circum0();
                return;
            } else {
                ProblemFlow("ProblemFlow_NoRoutingInformation", taskName);
                return;
            }
        }

        // Flow Route End tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route End", newParamsPage.getString("AssignTo"),
                mFlowHandle, taskName);

        if (bCalledNonExistentRule == true) {
            bCalledNonExistentRule = false;
            // We are in draft mode, and the routing activity wasn't found
            newParamsPage.putString("UseCurOperIfBasketNotFound", "true");
        } else
            // Default the UseCurOperIfBasketNotFound parameter
            newParamsPage.putString("UseCurOperIfBasketNotFound", "false");
        newParamsPage.putString("TaskLabel", "Step D");

        try {
            // Expression: TreeNavigation7
            newParamsPage.putString("HarnessPurpose", "TreeNavigation7");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusWork", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("DoNotPerform", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("Instructions", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusAssign", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: CUSTOM
            newParamsPage.putString("ViewPolicy", "CUSTOM");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: pyStepRoutedConfirmation
            newParamsPage.putString("ConfirmationNote",
                    "pyStepRoutedConfirmation");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        // Trace Assign Start Event
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity, flowName + " - " + taskName,
                "Flow Assign Start", "", mFlowHandle, taskName);

        // Run this Task's activity (RunTaskActivity returns false if it encountered a flow problem)
        if (!RunTaskActivity(taskName, taskActivity, workPage, newParamsPage))
            return;

        // Now find the newly created assignment page
        String strAssignmentPageName = newParamsPage
                .getString("AssignmentPageName");
        if (strAssignmentPageName == null)
            strAssignmentPageName = "newAssignPage";
        ClipboardPage pg_newAssignPage = tools.findPage(strAssignmentPageName);
        if (pg_newAssignPage == null) {
            // We were unable to find the assignment page; abort.
            ProblemFlow("ProblemFlow_AssignmentPageNotFound", taskName);
            return;
        }

        if (routeTo.length() > 0
                && !pega_rules_utilities.isOrInheritsFrom(tools,
                        pg_newAssignPage, "Assign-Worklist")) {
            // We need to switch this assignment over to a worklist in order to route it to the previous operator
            ClipboardPage tempPage = tools.getThread().createPage(
                    "Assign-Worklist", "");
            pg_newAssignPage.putAll(tempPage, ClipboardPage.PUTALL_KEEPNEW);
        }

        // Merge the settings in the temporary assignment page into the new assignment page
        // pxUrgencyAssignSLA was added to the assignment page by a rule-declare-expression
        pg_newAssignPage.remove(".pxUrgencyAssignSLA");
        try {
            pg_newAssignPage.putAll(pg_tempAssignPage,
                    ClipboardPage.PUTALL_MODEL);
        } catch (PRRuntimeException pre) {
            ProblemFlow(pre, taskName);
        } finally {
            pg_tempAssignPage.removeFromClipboard();
        }

        pg_newAssignPage.getProperty(".pxSystemFlow").setValue(false);
        pg_newAssignPage.putString(".pxFlowInsKey", mFlowHandle);

        if (pg_newAssignPage.getClassName().equals("Assign-Internal")
                && pg_newAssignPage.getString(".pxServiceLevelName").equals(""))
            currentFlow.getProperty(".pxAssignIsVirtual").setValue(true);

        StringMap tempParams = new HashStringMap();
        tempParams.putString("pxObjClass", "Rule-Obj-When");
        tempParams.putString("pyClassName", "Work-");
        tempParams.putString("pyBlockName", "pzSaveAssignPage");
        boolean bSaveAssignPage = tools.evaluateWhen(tempParams);
        if (!bSaveAssignPage)
            pg_newAssignPage.getProperty("pzTemporaryObject").setValue(true);
        if (bSaveAssignPage && !isTemporaryObject
                && !currentFlow.getBoolean(".pxAssignIsVirtual")) {
            // Call the AddAssign activity which saves the assignment
            if (!RunTaskActivity(taskName, "AddAssign", pg_newAssignPage,
                    newParamsPage))
                return;
        }
        // Update the flow with information from the parameter and assignment page
        currentFlow.putString("pxRouteTo",
                pg_newAssignPage.getString(".pxAssignedOperatorID"));
        if (newParamsPage.getString("Instructions") != null)
            currentFlow.putString("pyInstructions",
                    newParamsPage.getString("Instructions"));
        else
            currentFlow.putString("pyInstructions", "");

        if (newParamsPage.getString("ConfirmationNote") != null)
            currentFlow.putString("pyConfirmationNote",
                    newParamsPage.getString("ConfirmationNote"));
        else
            currentFlow.putString("pyConfirmationNote", "");

        currentFlow.putString(".pxAssignActivity", taskActivity);
        currentFlow
                .putString(".pxAssignClass", pg_newAssignPage.getClassName());
        if (currentFlow.getString(".pxAssignClass").equals("Assign-Worklist"))
            currentFlow.putString(".pxRouteToUserName",
                    pg_newAssignPage.getString(".pxAssignedUserName"));
        else
            currentFlow.putString(".pxRouteToUserName", "");
        try {
            currentFlow.putString(".pxAssignmentKey", tools.getDatabase()
                    .getHandle(pg_newAssignPage));
        } catch (DatabaseException de) {
        }
        currentFlow.putString(".pxAssignDeadTime",
                pg_newAssignPage.getString(".pxDeadlineTime"));
        currentFlow.putString(".pxAssignGoalTime",
                pg_newAssignPage.getString(".pxGoalTime"));
        currentFlow.putString(".pxAssignSvcLevel",
                pg_newAssignPage.getString(".pxServiceLevelName"));
        currentFlow.putString(".pxAssignStatus",
                pg_newAssignPage.getString(".pyAssignmentStatus"));

        String strTraceMessage = "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity;
        String strTraceStep = flowName + " - " + taskName;
        String strTraceEvent = "Flow Assign End";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "Step D");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "ASSIGNMENT", strTaskLabel, true, "",
                    false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        if (!isTemporaryObject) {
            // Add history for this assignment
            String strRouteTo = currentFlow.getString(".pxRouteToUserName");
            if (strRouteTo.equals(""))
                strRouteTo = currentFlow.getString(".pxRouteTo");
            String histMemo = "AssignmentRouted\t" + strRouteTo + "\t"
                    + currentFlow.getString(".pyInstructions");
            pega_procom_flow.AddHistory(tools, workPage, 'A', pg_newAssignPage,
                    histMemo);
        }

        //The send notification option is set to USE CASE TYPE POLICY on this assignment. Call the case type to get the notification settings
        ParameterPage notificationParams = new ParameterPage();
        pega_rules_utilities.pxCallCaseType(tools.getStepPage(),
                "GETCASENOTIFICATIONS", notificationParams);
        String isEmailNotificationEnabled = notificationParams
                .getString("EmailNotificationIsEnabled");
        String isPushNotificationEnabled = notificationParams
                .getString("PushNotificationIsEnabled");
        if (isEmailNotificationEnabled != null) {
            if (isEmailNotificationEnabled.equals("true")
                    || isPushNotificationEnabled.equals("true")) {
                //Check if the current user is not same as the assignee
                String strCurrentUser = tools.findPage("pxRequestor")
                        .getString(".pyUserIdentifier");
                String strAssignee = tools.findPage("newAssignPage").getString(
                        ".pxAssignedOperatorID");
                if (!strCurrentUser.equals(strAssignee)) {
                    StringMap notificationKeys = new HashStringMap();
                    notificationKeys.putString("pxObjClass",
                            "Rule-Obj-Activity");
                    notificationKeys.putString("pyClassName", "Work-");
                    notificationKeys.putString("pyActivityName",
                            "pzCaseNotification");
                    try {
                        tools.doActivity(notificationKeys, tools.getStepPage(),
                                notificationParams);
                    } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                        tools.getStepStatus().backoutWorst();
                    }
                }
            }
        }
        LogTraceEvent(mRuleSet, mFlowHandle, flowName + " - " + taskName,
                "Flow Idle", "", mFlowHandle, taskName);
        flowIdle = true;
    }

    private boolean IsConnectorFlowAction_AssignmentSF4_circum0(String strAction) {
        if (strAction.equals("ActionStub"))
            return true;
        return false;
    }

    // TaskFlowActions returns false if none of the actions could be added to the assignment
    private boolean TaskFlowActions_AssignmentSF4_circum0(
            ClipboardPage assignPage) {
        boolean isFirstAction = true;
        if (AddFlowAction("ActionStub", "FlowAction", isFirstAction, false))
            isFirstAction = false;
        return !isFirstAction;
    }

    private void TaskConnectors_AssignmentSF4_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "STATUS";
        strConnectorValue = "ActionStub";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "ActionStub", flowName, "Flow Connector",
                    "", mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "AssignmentSF1";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private void Task_Decision1_circum0() {
        String taskName = "Decision1";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "Decision1");
        currentFlow.putString(".pyCurrentFlowStepLabel", "Selected?");

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        String strTraceMessage = "";
        String strTraceStep = flowName;
        String strTraceEvent = "Flow Fork";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "Selected?");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "DECISION", strTaskLabel, false, "",
                    false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        TaskConnectors_Decision1_circum0();
    }

    private void TaskConnectors_Decision1_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "WHEN";
        strConnectorValue = "IsSelected";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet,
                    "Rule-Obj-When " + interestPage.getClassName()
                            + ".IsSelected", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "AssignmentSF3";
            return;
        } else if (connectorFlowProblem == true)
            return;

        strConnectorType = "ELSE";
        strConnectorValue = "true";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "ELSE", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "AssignmentSF4";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private void Task_END66_circum0() {
        String taskName = "END66";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "END66");
        currentFlow.putString(".pyCurrentFlowStepLabel", "");

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        // Set step ending properties
        currentFlow.putString(".pyLastFlowStep", taskName);
        currentFlow.putString(".pxLastActivityStatus",
                tools.findPage("pxThread").getString(".pxTaskStatus"));

        // All we need to do on a FLOWEND is set the flag to true
        flowHasEnded = true;
    }

    private void Task_SubProcessSF1_circum0() {
        String taskName = "SubProcessSF1";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "SubProcessSF1");
        currentFlow.putString(".pyCurrentFlowStepLabel", "MySubFlow");
        String taskActivity = "MySubFlow";

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        String flowInterestPage = "";

        // Flow Call Flow Start tracer message
        LogTraceEvent(mRuleSet, "Rule-Obj-Flow OOO-PGB-Work-Demo.MySubFlow",
                flowName + " - " + taskName, "Flow Call Flow Start", "",
                mFlowHandle, taskName);

        // Expression: MySubFlow
        if (LaunchFlow("Current", taskName, "FLOW", "", "", flowInterestPage,
                newParamsPage, "MySubFlow", true, "") == false)
            return;

        String strTraceMessage = "Rule-Obj-Flow OOO-PGB-Work-Demo.MySubFlow";
        String strTraceStep = flowName + " - " + taskName;
        String strTraceEvent = "Flow Call Flow End";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "MySubFlow");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "FLOW", strTaskLabel, true, "", false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        if (newParamsPage.getString("FlowHasEnded") == null
                || newParamsPage.getString("FlowHasEnded").equals("N")) {
            LogTraceEvent(mRuleSet, mFlowHandle, flowName + " - " + taskName,
                    "Flow Idle", "", mFlowHandle, taskName);
            flowIdle = true;
        } else {
            TaskConnectors_SubProcessSF1_circum0();
        }
    }

    private void TaskConnectors_SubProcessSF1_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "ALWAYS";
        strConnectorValue = "";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "ALWAYS", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "END66";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private void Task_Start62_circum0() {
        String taskName = "Start62";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "Start62");
        currentFlow.putString(".pyCurrentFlowStepLabel", "");

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        try {
            // Expression: 
            newParamsPage.putString("pxObjClass", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("CheckAvailability", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        // Create a temporary assignment page for the routing activity to access
        ClipboardPage pg_tempAssignPage = tools.createPage("Assign-",
                "AssignDefaults");
        newParamsPage.putString("AssignPage", "AssignDefaults");

        // Update the assignment with the routing skills information

        // Flow Route Start tracer message
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + ".ToCurrentOperator", flowName + " - " + taskName,
                "Flow Route Start", "", mFlowHandle, taskName);

        newParamsPage.putString("AssignTo", "");

        try {
            // Expression: 
            newParamsPage.putString("pxObjClass", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("CheckAvailability", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }
        // Run the routing activity (RunTaskActivity returns false if it encountered a flow problem)
        if (!RunTaskActivity(taskName, "ToCurrentOperator", workPage,
                newParamsPage))
            return;

        if (pg_tempAssignPage.getString(".pxAssignedOperatorID").length() > 0)
            pyFlowParametersPage.putString("DefaultAssignTo",
                    newParamsPage.getString("AssignTo"));
        else if (newParamsPage.getString("AssignTo").length() > 0) {
            pyFlowParametersPage.putString("DefaultAssignTo",
                    newParamsPage.getString("AssignTo"));
            if (newParamsPage.isDefined("SwitchToWorkbasket"))
                pyFlowParametersPage.putString("SwitchToWorkbasket",
                        newParamsPage.getString("SwitchToWorkbasket"));
        } else
            pyFlowParametersPage.putString("DefaultAssignTo", "");

        pg_tempAssignPage.removeFromClipboard();

        // Flow Route End tracer message
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + ".ToCurrentOperator", flowName + " - " + taskName,
                "Flow Route End",
                pyFlowParametersPage.getString("DefaultAssignTo"), mFlowHandle,
                taskName);

        // Set step ending properties
        currentFlow.putString(".pyLastFlowStep", taskName);
        currentFlow.putString(".pxLastActivityStatus",
                tools.findPage("pxThread").getString(".pxTaskStatus"));

        TaskConnectors_Start62_circum0();
    }

    private void TaskConnectors_Start62_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "ALWAYS";
        strConnectorValue = "";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "ALWAYS", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "AssignmentSF1";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private boolean[] TaskLocalActions_AssignmentSF1_circum0(
            ClipboardPage assignPage, boolean isFirstAction,
            boolean isFirstLocalAction) {
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    private void Task_AssignmentSF1_circum0() {
        String taskName = "AssignmentSF1";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "AssignmentSF1");
        currentFlow.putString(".pyCurrentFlowStepLabel", "Step A");
        String taskActivity = "WorkList";

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        // Create a temporary assignment page for the routing activity to access
        ClipboardPage pg_tempAssignPage = tools.createPage("Assign-",
                "AssignDefaults");
        newParamsPage.putString("AssignPage", "AssignDefaults");

        String routeTo = "";
        ClipboardProperty cpUsePreviousOperator = currentFlow
                .getIfPresent("pyUsePreviousOperator");
        if (cpUsePreviousOperator != null
                && cpUsePreviousOperator.toBoolean() == true) {
            // See if a previous operator can be located in the breadcrumb trail.
            ClipboardProperty taskPath = currentFlow.getProperty(".pyFlowPath");
            java.util.Iterator taskIt = taskPath.iterator();
            while (taskIt.hasNext()) {
                ClipboardProperty taskPageRef = (ClipboardProperty) taskIt
                        .next();
                ClipboardPage taskPage = taskPageRef.getPageValue();
                String strHistoryTaskName = taskPage.getString(".pyFlowStep");
                if (strHistoryTaskName.equals(taskName)) {
                    routeTo = taskPage.getString("pyRouteTo");
                    // Make sure the routing operator is available
                    if (routeTo.length() > 0)
                        routeTo = pega_procom_routing.checkForSubstitute(
                                routeTo, workPage, pg_tempAssignPage, tools);
                    break;
                }
            }
        }

        // Flow Route Start tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route Start", "", mFlowHandle, taskName);

        String defaultAssignTo = pyFlowParametersPage
                .getString("DefaultAssignTo");
        if (defaultAssignTo != null && defaultAssignTo.length() > 0)
            newParamsPage.putString("AssignTo", defaultAssignTo);
        else {
            if (!RunTaskActivity(taskName, "ToCurrentOperator", workPage,
                    newParamsPage))
                return;
        }

        if (pg_tempAssignPage.getString(".pxAssignedOperatorID").length() > 0) {
            newParamsPage.putString("AssignTo",
                    pg_tempAssignPage.getString(".pxAssignedOperatorID"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else if (newParamsPage.getString("AssignTo").length() > 0) {
            pg_tempAssignPage.putString(".pxAssignedOperatorID",
                    newParamsPage.getString("AssignTo"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else {
            pg_tempAssignPage.removeFromClipboard();

            // Check if we should short-circuit this assignment
            String passThrough = pega_rules_default
                    .getCheckBoxBoolean(newParamsPage
                            .getString("PassThroughAssignment"));
            if (passThrough.equals("true")) {
                String actionToTake = newParamsPage
                        .getString("FlowActionToTake");
                if (actionToTake == null || actionToTake.equals("")) {
                    actionToTake = "StepA";
                }
                currentFlow.putString(".pxLastActivityStatus", actionToTake);
                tools.findPage("pxThread").putString(".pxTaskStatus",
                        actionToTake);
                currentFlow.putString(".pyLastFlowStep", taskName);

                // Flow Route End tracer message
                LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                        "Flow Route End", "Assignment Skipped", mFlowHandle,
                        taskName);

                TaskConnectors_AssignmentSF1_circum0();
                return;
            } else {
                ProblemFlow("ProblemFlow_NoRoutingInformation", taskName);
                return;
            }
        }

        // Flow Route End tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route End", newParamsPage.getString("AssignTo"),
                mFlowHandle, taskName);

        if (bCalledNonExistentRule == true) {
            bCalledNonExistentRule = false;
            // We are in draft mode, and the routing activity wasn't found
            newParamsPage.putString("UseCurOperIfBasketNotFound", "true");
        } else
            // Default the UseCurOperIfBasketNotFound parameter
            newParamsPage.putString("UseCurOperIfBasketNotFound", "false");
        newParamsPage.putString("TaskLabel", "Step A");

        try {
            // Expression: TreeNavigation7
            newParamsPage.putString("HarnessPurpose", "TreeNavigation7");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusWork", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("DoNotPerform", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("Instructions", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusAssign", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: CUSTOM
            newParamsPage.putString("ViewPolicy", "CUSTOM");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: pyStepRoutedConfirmation
            newParamsPage.putString("ConfirmationNote",
                    "pyStepRoutedConfirmation");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        // Trace Assign Start Event
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity, flowName + " - " + taskName,
                "Flow Assign Start", "", mFlowHandle, taskName);

        // Run this Task's activity (RunTaskActivity returns false if it encountered a flow problem)
        if (!RunTaskActivity(taskName, taskActivity, workPage, newParamsPage))
            return;

        // Now find the newly created assignment page
        String strAssignmentPageName = newParamsPage
                .getString("AssignmentPageName");
        if (strAssignmentPageName == null)
            strAssignmentPageName = "newAssignPage";
        ClipboardPage pg_newAssignPage = tools.findPage(strAssignmentPageName);
        if (pg_newAssignPage == null) {
            // We were unable to find the assignment page; abort.
            ProblemFlow("ProblemFlow_AssignmentPageNotFound", taskName);
            return;
        }

        if (routeTo.length() > 0
                && !pega_rules_utilities.isOrInheritsFrom(tools,
                        pg_newAssignPage, "Assign-Worklist")) {
            // We need to switch this assignment over to a worklist in order to route it to the previous operator
            ClipboardPage tempPage = tools.getThread().createPage(
                    "Assign-Worklist", "");
            pg_newAssignPage.putAll(tempPage, ClipboardPage.PUTALL_KEEPNEW);
        }

        // Merge the settings in the temporary assignment page into the new assignment page
        // pxUrgencyAssignSLA was added to the assignment page by a rule-declare-expression
        pg_newAssignPage.remove(".pxUrgencyAssignSLA");
        try {
            pg_newAssignPage.putAll(pg_tempAssignPage,
                    ClipboardPage.PUTALL_MODEL);
        } catch (PRRuntimeException pre) {
            ProblemFlow(pre, taskName);
        } finally {
            pg_tempAssignPage.removeFromClipboard();
        }

        pg_newAssignPage.getProperty(".pxSystemFlow").setValue(false);
        pg_newAssignPage.putString(".pxFlowInsKey", mFlowHandle);

        if (pg_newAssignPage.getClassName().equals("Assign-Internal")
                && pg_newAssignPage.getString(".pxServiceLevelName").equals(""))
            currentFlow.getProperty(".pxAssignIsVirtual").setValue(true);

        StringMap tempParams = new HashStringMap();
        tempParams.putString("pxObjClass", "Rule-Obj-When");
        tempParams.putString("pyClassName", "Work-");
        tempParams.putString("pyBlockName", "pzSaveAssignPage");
        boolean bSaveAssignPage = tools.evaluateWhen(tempParams);
        if (!bSaveAssignPage)
            pg_newAssignPage.getProperty("pzTemporaryObject").setValue(true);
        if (bSaveAssignPage && !isTemporaryObject
                && !currentFlow.getBoolean(".pxAssignIsVirtual")) {
            // Call the AddAssign activity which saves the assignment
            if (!RunTaskActivity(taskName, "AddAssign", pg_newAssignPage,
                    newParamsPage))
                return;
        }
        // Update the flow with information from the parameter and assignment page
        currentFlow.putString("pxRouteTo",
                pg_newAssignPage.getString(".pxAssignedOperatorID"));
        if (newParamsPage.getString("Instructions") != null)
            currentFlow.putString("pyInstructions",
                    newParamsPage.getString("Instructions"));
        else
            currentFlow.putString("pyInstructions", "");

        if (newParamsPage.getString("ConfirmationNote") != null)
            currentFlow.putString("pyConfirmationNote",
                    newParamsPage.getString("ConfirmationNote"));
        else
            currentFlow.putString("pyConfirmationNote", "");

        currentFlow.putString(".pxAssignActivity", taskActivity);
        currentFlow
                .putString(".pxAssignClass", pg_newAssignPage.getClassName());
        if (currentFlow.getString(".pxAssignClass").equals("Assign-Worklist"))
            currentFlow.putString(".pxRouteToUserName",
                    pg_newAssignPage.getString(".pxAssignedUserName"));
        else
            currentFlow.putString(".pxRouteToUserName", "");
        try {
            currentFlow.putString(".pxAssignmentKey", tools.getDatabase()
                    .getHandle(pg_newAssignPage));
        } catch (DatabaseException de) {
        }
        currentFlow.putString(".pxAssignDeadTime",
                pg_newAssignPage.getString(".pxDeadlineTime"));
        currentFlow.putString(".pxAssignGoalTime",
                pg_newAssignPage.getString(".pxGoalTime"));
        currentFlow.putString(".pxAssignSvcLevel",
                pg_newAssignPage.getString(".pxServiceLevelName"));
        currentFlow.putString(".pxAssignStatus",
                pg_newAssignPage.getString(".pyAssignmentStatus"));

        String strTraceMessage = "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity;
        String strTraceStep = flowName + " - " + taskName;
        String strTraceEvent = "Flow Assign End";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "Step A");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "ASSIGNMENT", strTaskLabel, true, "",
                    false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        if (!isTemporaryObject) {
            // Add history for this assignment
            String strRouteTo = currentFlow.getString(".pxRouteToUserName");
            if (strRouteTo.equals(""))
                strRouteTo = currentFlow.getString(".pxRouteTo");
            String histMemo = "AssignmentRouted\t" + strRouteTo + "\t"
                    + currentFlow.getString(".pyInstructions");
            pega_procom_flow.AddHistory(tools, workPage, 'A', pg_newAssignPage,
                    histMemo);
        }

        //The send notification option is set to USE CASE TYPE POLICY on this assignment. Call the case type to get the notification settings
        ParameterPage notificationParams = new ParameterPage();
        pega_rules_utilities.pxCallCaseType(tools.getStepPage(),
                "GETCASENOTIFICATIONS", notificationParams);
        String isEmailNotificationEnabled = notificationParams
                .getString("EmailNotificationIsEnabled");
        String isPushNotificationEnabled = notificationParams
                .getString("PushNotificationIsEnabled");
        if (isEmailNotificationEnabled != null) {
            if (isEmailNotificationEnabled.equals("true")
                    || isPushNotificationEnabled.equals("true")) {
                //Check if the current user is not same as the assignee
                String strCurrentUser = tools.findPage("pxRequestor")
                        .getString(".pyUserIdentifier");
                String strAssignee = tools.findPage("newAssignPage").getString(
                        ".pxAssignedOperatorID");
                if (!strCurrentUser.equals(strAssignee)) {
                    StringMap notificationKeys = new HashStringMap();
                    notificationKeys.putString("pxObjClass",
                            "Rule-Obj-Activity");
                    notificationKeys.putString("pyClassName", "Work-");
                    notificationKeys.putString("pyActivityName",
                            "pzCaseNotification");
                    try {
                        tools.doActivity(notificationKeys, tools.getStepPage(),
                                notificationParams);
                    } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                        tools.getStepStatus().backoutWorst();
                    }
                }
            }
        }
        LogTraceEvent(mRuleSet, mFlowHandle, flowName + " - " + taskName,
                "Flow Idle", "", mFlowHandle, taskName);
        flowIdle = true;
    }

    private boolean IsConnectorFlowAction_AssignmentSF1_circum0(String strAction) {
        if (strAction.equals("StepA"))
            return true;
        return false;
    }

    // TaskFlowActions returns false if none of the actions could be added to the assignment
    private boolean TaskFlowActions_AssignmentSF1_circum0(
            ClipboardPage assignPage) {
        boolean isFirstAction = true;
        if (AddFlowAction("StepA", "FlowAction", isFirstAction, false))
            isFirstAction = false;
        return !isFirstAction;
    }

    private void TaskConnectors_AssignmentSF1_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "STATUS";
        strConnectorValue = "StepA";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "StepA", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "AssignmentSF2";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private boolean[] TaskLocalActions_AssignmentSF2_circum0(
            ClipboardPage assignPage, boolean isFirstAction,
            boolean isFirstLocalAction) {
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    private void Task_AssignmentSF2_circum0() {
        String taskName = "AssignmentSF2";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "AssignmentSF2");
        currentFlow.putString(".pyCurrentFlowStepLabel", "Step B");
        String taskActivity = "WorkList";

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        // Create a temporary assignment page for the routing activity to access
        ClipboardPage pg_tempAssignPage = tools.createPage("Assign-",
                "AssignDefaults");
        newParamsPage.putString("AssignPage", "AssignDefaults");

        String routeTo = "";
        ClipboardProperty cpUsePreviousOperator = currentFlow
                .getIfPresent("pyUsePreviousOperator");
        if (cpUsePreviousOperator != null
                && cpUsePreviousOperator.toBoolean() == true) {
            // See if a previous operator can be located in the breadcrumb trail.
            ClipboardProperty taskPath = currentFlow.getProperty(".pyFlowPath");
            java.util.Iterator taskIt = taskPath.iterator();
            while (taskIt.hasNext()) {
                ClipboardProperty taskPageRef = (ClipboardProperty) taskIt
                        .next();
                ClipboardPage taskPage = taskPageRef.getPageValue();
                String strHistoryTaskName = taskPage.getString(".pyFlowStep");
                if (strHistoryTaskName.equals(taskName)) {
                    routeTo = taskPage.getString("pyRouteTo");
                    // Make sure the routing operator is available
                    if (routeTo.length() > 0)
                        routeTo = pega_procom_routing.checkForSubstitute(
                                routeTo, workPage, pg_tempAssignPage, tools);
                    break;
                }
            }
        }

        // Flow Route Start tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route Start", "", mFlowHandle, taskName);

        String defaultAssignTo = pyFlowParametersPage
                .getString("DefaultAssignTo");
        if (defaultAssignTo != null && defaultAssignTo.length() > 0)
            newParamsPage.putString("AssignTo", defaultAssignTo);
        else {
            if (!RunTaskActivity(taskName, "ToCurrentOperator", workPage,
                    newParamsPage))
                return;
        }

        if (pg_tempAssignPage.getString(".pxAssignedOperatorID").length() > 0) {
            newParamsPage.putString("AssignTo",
                    pg_tempAssignPage.getString(".pxAssignedOperatorID"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else if (newParamsPage.getString("AssignTo").length() > 0) {
            pg_tempAssignPage.putString(".pxAssignedOperatorID",
                    newParamsPage.getString("AssignTo"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else {
            pg_tempAssignPage.removeFromClipboard();

            // Check if we should short-circuit this assignment
            String passThrough = pega_rules_default
                    .getCheckBoxBoolean(newParamsPage
                            .getString("PassThroughAssignment"));
            if (passThrough.equals("true")) {
                String actionToTake = newParamsPage
                        .getString("FlowActionToTake");
                if (actionToTake == null || actionToTake.equals("")) {
                    actionToTake = "StepB";
                }
                currentFlow.putString(".pxLastActivityStatus", actionToTake);
                tools.findPage("pxThread").putString(".pxTaskStatus",
                        actionToTake);
                currentFlow.putString(".pyLastFlowStep", taskName);

                // Flow Route End tracer message
                LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                        "Flow Route End", "Assignment Skipped", mFlowHandle,
                        taskName);

                TaskConnectors_AssignmentSF2_circum0();
                return;
            } else {
                ProblemFlow("ProblemFlow_NoRoutingInformation", taskName);
                return;
            }
        }

        // Flow Route End tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route End", newParamsPage.getString("AssignTo"),
                mFlowHandle, taskName);

        if (bCalledNonExistentRule == true) {
            bCalledNonExistentRule = false;
            // We are in draft mode, and the routing activity wasn't found
            newParamsPage.putString("UseCurOperIfBasketNotFound", "true");
        } else
            // Default the UseCurOperIfBasketNotFound parameter
            newParamsPage.putString("UseCurOperIfBasketNotFound", "false");
        newParamsPage.putString("TaskLabel", "Step B");

        try {
            // Expression: TreeNavigation7
            newParamsPage.putString("HarnessPurpose", "TreeNavigation7");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusWork", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("DoNotPerform", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("Instructions", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusAssign", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: CUSTOM
            newParamsPage.putString("ViewPolicy", "CUSTOM");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: pyStepRoutedConfirmation
            newParamsPage.putString("ConfirmationNote",
                    "pyStepRoutedConfirmation");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        // Trace Assign Start Event
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity, flowName + " - " + taskName,
                "Flow Assign Start", "", mFlowHandle, taskName);

        // Run this Task's activity (RunTaskActivity returns false if it encountered a flow problem)
        if (!RunTaskActivity(taskName, taskActivity, workPage, newParamsPage))
            return;

        // Now find the newly created assignment page
        String strAssignmentPageName = newParamsPage
                .getString("AssignmentPageName");
        if (strAssignmentPageName == null)
            strAssignmentPageName = "newAssignPage";
        ClipboardPage pg_newAssignPage = tools.findPage(strAssignmentPageName);
        if (pg_newAssignPage == null) {
            // We were unable to find the assignment page; abort.
            ProblemFlow("ProblemFlow_AssignmentPageNotFound", taskName);
            return;
        }

        if (routeTo.length() > 0
                && !pega_rules_utilities.isOrInheritsFrom(tools,
                        pg_newAssignPage, "Assign-Worklist")) {
            // We need to switch this assignment over to a worklist in order to route it to the previous operator
            ClipboardPage tempPage = tools.getThread().createPage(
                    "Assign-Worklist", "");
            pg_newAssignPage.putAll(tempPage, ClipboardPage.PUTALL_KEEPNEW);
        }

        // Merge the settings in the temporary assignment page into the new assignment page
        // pxUrgencyAssignSLA was added to the assignment page by a rule-declare-expression
        pg_newAssignPage.remove(".pxUrgencyAssignSLA");
        try {
            pg_newAssignPage.putAll(pg_tempAssignPage,
                    ClipboardPage.PUTALL_MODEL);
        } catch (PRRuntimeException pre) {
            ProblemFlow(pre, taskName);
        } finally {
            pg_tempAssignPage.removeFromClipboard();
        }

        pg_newAssignPage.getProperty(".pxSystemFlow").setValue(false);
        pg_newAssignPage.putString(".pxFlowInsKey", mFlowHandle);

        if (pg_newAssignPage.getClassName().equals("Assign-Internal")
                && pg_newAssignPage.getString(".pxServiceLevelName").equals(""))
            currentFlow.getProperty(".pxAssignIsVirtual").setValue(true);

        StringMap tempParams = new HashStringMap();
        tempParams.putString("pxObjClass", "Rule-Obj-When");
        tempParams.putString("pyClassName", "Work-");
        tempParams.putString("pyBlockName", "pzSaveAssignPage");
        boolean bSaveAssignPage = tools.evaluateWhen(tempParams);
        if (!bSaveAssignPage)
            pg_newAssignPage.getProperty("pzTemporaryObject").setValue(true);
        if (bSaveAssignPage && !isTemporaryObject
                && !currentFlow.getBoolean(".pxAssignIsVirtual")) {
            // Call the AddAssign activity which saves the assignment
            if (!RunTaskActivity(taskName, "AddAssign", pg_newAssignPage,
                    newParamsPage))
                return;
        }
        // Update the flow with information from the parameter and assignment page
        currentFlow.putString("pxRouteTo",
                pg_newAssignPage.getString(".pxAssignedOperatorID"));
        if (newParamsPage.getString("Instructions") != null)
            currentFlow.putString("pyInstructions",
                    newParamsPage.getString("Instructions"));
        else
            currentFlow.putString("pyInstructions", "");

        if (newParamsPage.getString("ConfirmationNote") != null)
            currentFlow.putString("pyConfirmationNote",
                    newParamsPage.getString("ConfirmationNote"));
        else
            currentFlow.putString("pyConfirmationNote", "");

        currentFlow.putString(".pxAssignActivity", taskActivity);
        currentFlow
                .putString(".pxAssignClass", pg_newAssignPage.getClassName());
        if (currentFlow.getString(".pxAssignClass").equals("Assign-Worklist"))
            currentFlow.putString(".pxRouteToUserName",
                    pg_newAssignPage.getString(".pxAssignedUserName"));
        else
            currentFlow.putString(".pxRouteToUserName", "");
        try {
            currentFlow.putString(".pxAssignmentKey", tools.getDatabase()
                    .getHandle(pg_newAssignPage));
        } catch (DatabaseException de) {
        }
        currentFlow.putString(".pxAssignDeadTime",
                pg_newAssignPage.getString(".pxDeadlineTime"));
        currentFlow.putString(".pxAssignGoalTime",
                pg_newAssignPage.getString(".pxGoalTime"));
        currentFlow.putString(".pxAssignSvcLevel",
                pg_newAssignPage.getString(".pxServiceLevelName"));
        currentFlow.putString(".pxAssignStatus",
                pg_newAssignPage.getString(".pyAssignmentStatus"));

        String strTraceMessage = "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity;
        String strTraceStep = flowName + " - " + taskName;
        String strTraceEvent = "Flow Assign End";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "Step B");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "ASSIGNMENT", strTaskLabel, true, "",
                    false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        if (!isTemporaryObject) {
            // Add history for this assignment
            String strRouteTo = currentFlow.getString(".pxRouteToUserName");
            if (strRouteTo.equals(""))
                strRouteTo = currentFlow.getString(".pxRouteTo");
            String histMemo = "AssignmentRouted\t" + strRouteTo + "\t"
                    + currentFlow.getString(".pyInstructions");
            pega_procom_flow.AddHistory(tools, workPage, 'A', pg_newAssignPage,
                    histMemo);
        }

        //The send notification option is set to USE CASE TYPE POLICY on this assignment. Call the case type to get the notification settings
        ParameterPage notificationParams = new ParameterPage();
        pega_rules_utilities.pxCallCaseType(tools.getStepPage(),
                "GETCASENOTIFICATIONS", notificationParams);
        String isEmailNotificationEnabled = notificationParams
                .getString("EmailNotificationIsEnabled");
        String isPushNotificationEnabled = notificationParams
                .getString("PushNotificationIsEnabled");
        if (isEmailNotificationEnabled != null) {
            if (isEmailNotificationEnabled.equals("true")
                    || isPushNotificationEnabled.equals("true")) {
                //Check if the current user is not same as the assignee
                String strCurrentUser = tools.findPage("pxRequestor")
                        .getString(".pyUserIdentifier");
                String strAssignee = tools.findPage("newAssignPage").getString(
                        ".pxAssignedOperatorID");
                if (!strCurrentUser.equals(strAssignee)) {
                    StringMap notificationKeys = new HashStringMap();
                    notificationKeys.putString("pxObjClass",
                            "Rule-Obj-Activity");
                    notificationKeys.putString("pyClassName", "Work-");
                    notificationKeys.putString("pyActivityName",
                            "pzCaseNotification");
                    try {
                        tools.doActivity(notificationKeys, tools.getStepPage(),
                                notificationParams);
                    } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                        tools.getStepStatus().backoutWorst();
                    }
                }
            }
        }
        LogTraceEvent(mRuleSet, mFlowHandle, flowName + " - " + taskName,
                "Flow Idle", "", mFlowHandle, taskName);
        flowIdle = true;
    }

    private boolean IsConnectorFlowAction_AssignmentSF2_circum0(String strAction) {
        if (strAction.equals("StepB"))
            return true;
        return false;
    }

    // TaskFlowActions returns false if none of the actions could be added to the assignment
    private boolean TaskFlowActions_AssignmentSF2_circum0(
            ClipboardPage assignPage) {
        boolean isFirstAction = true;
        if (AddFlowAction("StepB", "FlowAction", isFirstAction, false))
            isFirstAction = false;
        return !isFirstAction;
    }

    private void TaskConnectors_AssignmentSF2_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "STATUS";
        strConnectorValue = "StepB";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "StepB", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "Decision1";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private boolean[] TaskLocalActions_AssignmentSF3_circum0(
            ClipboardPage assignPage, boolean isFirstAction,
            boolean isFirstLocalAction) {
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    private void Task_AssignmentSF3_circum0() {
        String taskName = "AssignmentSF3";
        lastFlowStepRun = nextFlowStepToRun;
        nextFlowStepToRun = ""; // reset it
        bCalledNonExistentRule = false; // reset it
        currentFlow.putString("pyCurrentFlowStep", "AssignmentSF3");
        currentFlow.putString(".pyCurrentFlowStepLabel", "Step C");
        String taskActivity = "WorkList";

        // Create a vector of this task's contexts
        java.util.Vector currentContexts = new java.util.Vector();

        // Infinite loop checking code
        if (taskCalledCount.containsKey(taskName)) {
            Integer i = (Integer) taskCalledCount.get(taskName);
            if (i.intValue() > 500) {
                // we're probably in an infinite loop
                ProblemFlow("ProblemFlow_InfiniteInnerLoop", taskName);
                return;
            } else
                taskCalledCount.put(taskName, new Integer(i.intValue() + 1));
        } else
            taskCalledCount.put(taskName, new Integer(1));

        // Remove old contexts
        ClipboardProperty cpContexts = currentFlow.getProperty(".pyContexts");
        java.util.Iterator cons = cpContexts.iterator();
        while (cons.hasNext()) {
            ClipboardProperty cpContext = (ClipboardProperty) cons.next();
            ClipboardPage pgContext = cpContext.getPageValue();
            String strContextID = pgContext.getString(".pyContextID");
            if (!currentContexts.contains(strContextID)) {
                LeaveContext_circum0(strContextID);
            }
        }

        // Now add this task's new contexts 
        ClipboardProperty cpContext;

        // Create a parameter page for this task
        ParameterPage newParamsPage = createNewParamsPage(taskName);

        // Create a temporary assignment page for the routing activity to access
        ClipboardPage pg_tempAssignPage = tools.createPage("Assign-",
                "AssignDefaults");
        newParamsPage.putString("AssignPage", "AssignDefaults");

        String routeTo = "";
        ClipboardProperty cpUsePreviousOperator = currentFlow
                .getIfPresent("pyUsePreviousOperator");
        if (cpUsePreviousOperator != null
                && cpUsePreviousOperator.toBoolean() == true) {
            // See if a previous operator can be located in the breadcrumb trail.
            ClipboardProperty taskPath = currentFlow.getProperty(".pyFlowPath");
            java.util.Iterator taskIt = taskPath.iterator();
            while (taskIt.hasNext()) {
                ClipboardProperty taskPageRef = (ClipboardProperty) taskIt
                        .next();
                ClipboardPage taskPage = taskPageRef.getPageValue();
                String strHistoryTaskName = taskPage.getString(".pyFlowStep");
                if (strHistoryTaskName.equals(taskName)) {
                    routeTo = taskPage.getString("pyRouteTo");
                    // Make sure the routing operator is available
                    if (routeTo.length() > 0)
                        routeTo = pega_procom_routing.checkForSubstitute(
                                routeTo, workPage, pg_tempAssignPage, tools);
                    break;
                }
            }
        }

        // Flow Route Start tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route Start", "", mFlowHandle, taskName);

        String defaultAssignTo = pyFlowParametersPage
                .getString("DefaultAssignTo");
        if (defaultAssignTo != null && defaultAssignTo.length() > 0)
            newParamsPage.putString("AssignTo", defaultAssignTo);
        else {
            if (!RunTaskActivity(taskName, "ToCurrentOperator", workPage,
                    newParamsPage))
                return;
        }

        if (pg_tempAssignPage.getString(".pxAssignedOperatorID").length() > 0) {
            newParamsPage.putString("AssignTo",
                    pg_tempAssignPage.getString(".pxAssignedOperatorID"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else if (newParamsPage.getString("AssignTo").length() > 0) {
            pg_tempAssignPage.putString(".pxAssignedOperatorID",
                    newParamsPage.getString("AssignTo"));
            pyFlowParametersPage.putString("AssignTo",
                    newParamsPage.getString("AssignTo"));
        } else {
            pg_tempAssignPage.removeFromClipboard();

            // Check if we should short-circuit this assignment
            String passThrough = pega_rules_default
                    .getCheckBoxBoolean(newParamsPage
                            .getString("PassThroughAssignment"));
            if (passThrough.equals("true")) {
                String actionToTake = newParamsPage
                        .getString("FlowActionToTake");
                if (actionToTake == null || actionToTake.equals("")) {
                    actionToTake = "StepC";
                }
                currentFlow.putString(".pxLastActivityStatus", actionToTake);
                tools.findPage("pxThread").putString(".pxTaskStatus",
                        actionToTake);
                currentFlow.putString(".pyLastFlowStep", taskName);

                // Flow Route End tracer message
                LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                        "Flow Route End", "Assignment Skipped", mFlowHandle,
                        taskName);

                TaskConnectors_AssignmentSF3_circum0();
                return;
            } else {
                ProblemFlow("ProblemFlow_NoRoutingInformation", taskName);
                return;
            }
        }

        // Flow Route End tracer message
        LogTraceEvent(mRuleSet, "", flowName + " - " + taskName,
                "Flow Route End", newParamsPage.getString("AssignTo"),
                mFlowHandle, taskName);

        if (bCalledNonExistentRule == true) {
            bCalledNonExistentRule = false;
            // We are in draft mode, and the routing activity wasn't found
            newParamsPage.putString("UseCurOperIfBasketNotFound", "true");
        } else
            // Default the UseCurOperIfBasketNotFound parameter
            newParamsPage.putString("UseCurOperIfBasketNotFound", "false");
        newParamsPage.putString("TaskLabel", "Step C");

        try {
            // Expression: TreeNavigation7
            newParamsPage.putString("HarnessPurpose", "TreeNavigation7");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusWork", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("DoNotPerform", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("Instructions", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: 
            newParamsPage.putString("StatusAssign", "");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: CUSTOM
            newParamsPage.putString("ViewPolicy", "CUSTOM");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        try {
            // Expression: pyStepRoutedConfirmation
            newParamsPage.putString("ConfirmationNote",
                    "pyStepRoutedConfirmation");
        } catch (java.lang.IllegalArgumentException e) {
            if (!bDraftMode) {
                // We've got a flow problem; a page could have been passed in when a property was expected.
                ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                return;
            }
        }

        // Trace Assign Start Event
        LogTraceEvent(mRuleSet, "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity, flowName + " - " + taskName,
                "Flow Assign Start", "", mFlowHandle, taskName);

        // Run this Task's activity (RunTaskActivity returns false if it encountered a flow problem)
        if (!RunTaskActivity(taskName, taskActivity, workPage, newParamsPage))
            return;

        // Now find the newly created assignment page
        String strAssignmentPageName = newParamsPage
                .getString("AssignmentPageName");
        if (strAssignmentPageName == null)
            strAssignmentPageName = "newAssignPage";
        ClipboardPage pg_newAssignPage = tools.findPage(strAssignmentPageName);
        if (pg_newAssignPage == null) {
            // We were unable to find the assignment page; abort.
            ProblemFlow("ProblemFlow_AssignmentPageNotFound", taskName);
            return;
        }

        if (routeTo.length() > 0
                && !pega_rules_utilities.isOrInheritsFrom(tools,
                        pg_newAssignPage, "Assign-Worklist")) {
            // We need to switch this assignment over to a worklist in order to route it to the previous operator
            ClipboardPage tempPage = tools.getThread().createPage(
                    "Assign-Worklist", "");
            pg_newAssignPage.putAll(tempPage, ClipboardPage.PUTALL_KEEPNEW);
        }

        // Merge the settings in the temporary assignment page into the new assignment page
        // pxUrgencyAssignSLA was added to the assignment page by a rule-declare-expression
        pg_newAssignPage.remove(".pxUrgencyAssignSLA");
        try {
            pg_newAssignPage.putAll(pg_tempAssignPage,
                    ClipboardPage.PUTALL_MODEL);
        } catch (PRRuntimeException pre) {
            ProblemFlow(pre, taskName);
        } finally {
            pg_tempAssignPage.removeFromClipboard();
        }

        pg_newAssignPage.getProperty(".pxSystemFlow").setValue(false);
        pg_newAssignPage.putString(".pxFlowInsKey", mFlowHandle);

        if (pg_newAssignPage.getClassName().equals("Assign-Internal")
                && pg_newAssignPage.getString(".pxServiceLevelName").equals(""))
            currentFlow.getProperty(".pxAssignIsVirtual").setValue(true);

        StringMap tempParams = new HashStringMap();
        tempParams.putString("pxObjClass", "Rule-Obj-When");
        tempParams.putString("pyClassName", "Work-");
        tempParams.putString("pyBlockName", "pzSaveAssignPage");
        boolean bSaveAssignPage = tools.evaluateWhen(tempParams);
        if (!bSaveAssignPage)
            pg_newAssignPage.getProperty("pzTemporaryObject").setValue(true);
        if (bSaveAssignPage && !isTemporaryObject
                && !currentFlow.getBoolean(".pxAssignIsVirtual")) {
            // Call the AddAssign activity which saves the assignment
            if (!RunTaskActivity(taskName, "AddAssign", pg_newAssignPage,
                    newParamsPage))
                return;
        }
        // Update the flow with information from the parameter and assignment page
        currentFlow.putString("pxRouteTo",
                pg_newAssignPage.getString(".pxAssignedOperatorID"));
        if (newParamsPage.getString("Instructions") != null)
            currentFlow.putString("pyInstructions",
                    newParamsPage.getString("Instructions"));
        else
            currentFlow.putString("pyInstructions", "");

        if (newParamsPage.getString("ConfirmationNote") != null)
            currentFlow.putString("pyConfirmationNote",
                    newParamsPage.getString("ConfirmationNote"));
        else
            currentFlow.putString("pyConfirmationNote", "");

        currentFlow.putString(".pxAssignActivity", taskActivity);
        currentFlow
                .putString(".pxAssignClass", pg_newAssignPage.getClassName());
        if (currentFlow.getString(".pxAssignClass").equals("Assign-Worklist"))
            currentFlow.putString(".pxRouteToUserName",
                    pg_newAssignPage.getString(".pxAssignedUserName"));
        else
            currentFlow.putString(".pxRouteToUserName", "");
        try {
            currentFlow.putString(".pxAssignmentKey", tools.getDatabase()
                    .getHandle(pg_newAssignPage));
        } catch (DatabaseException de) {
        }
        currentFlow.putString(".pxAssignDeadTime",
                pg_newAssignPage.getString(".pxDeadlineTime"));
        currentFlow.putString(".pxAssignGoalTime",
                pg_newAssignPage.getString(".pxGoalTime"));
        currentFlow.putString(".pxAssignSvcLevel",
                pg_newAssignPage.getString(".pxServiceLevelName"));
        currentFlow.putString(".pxAssignStatus",
                pg_newAssignPage.getString(".pyAssignmentStatus"));

        String strTraceMessage = "Rule-Obj-Activity " + workPage.getClassName()
                + "." + taskActivity;
        String strTraceStep = flowName + " - " + taskName;
        String strTraceEvent = "Flow Assign End";
        String strTraceStatus = "";

        if (currentFlow != null) {
            // Set step ending properties now that things have completed gracefully
            currentFlow.putString(".pyCurrentFlowStep", "");
            currentFlow.putString(".pyCurrentFlowStepLabel", "");
            currentFlow.putString(".pyLastFlowStep", taskName);
            currentFlow.putString(".pxLastActivityStatus",
                    tools.findPage("pxThread").getString(".pxTaskStatus"));
            workUpdateDateTime = pega_rules_datetime.getCurrentTimeStamp();
            workPage.putString(".pxUpdateDateTime", workUpdateDateTime);

            currentFlow.putString(".pyLastFlowStepLabel", "Step C");
            String strTaskLabel = currentFlow.getString(".pyLastFlowStepLabel");
            AddTaskToFlowPath(taskName, "ASSIGNMENT", strTaskLabel, true, "",
                    false);
        }

        // Trace task ending event
        LogTraceEvent(mRuleSet, strTraceMessage, strTraceStep, strTraceEvent,
                strTraceStatus, mFlowHandle, taskName);

        if (!isTemporaryObject) {
            // Add history for this assignment
            String strRouteTo = currentFlow.getString(".pxRouteToUserName");
            if (strRouteTo.equals(""))
                strRouteTo = currentFlow.getString(".pxRouteTo");
            String histMemo = "AssignmentRouted\t" + strRouteTo + "\t"
                    + currentFlow.getString(".pyInstructions");
            pega_procom_flow.AddHistory(tools, workPage, 'A', pg_newAssignPage,
                    histMemo);
        }

        //The send notification option is set to USE CASE TYPE POLICY on this assignment. Call the case type to get the notification settings
        ParameterPage notificationParams = new ParameterPage();
        pega_rules_utilities.pxCallCaseType(tools.getStepPage(),
                "GETCASENOTIFICATIONS", notificationParams);
        String isEmailNotificationEnabled = notificationParams
                .getString("EmailNotificationIsEnabled");
        String isPushNotificationEnabled = notificationParams
                .getString("PushNotificationIsEnabled");
        if (isEmailNotificationEnabled != null) {
            if (isEmailNotificationEnabled.equals("true")
                    || isPushNotificationEnabled.equals("true")) {
                //Check if the current user is not same as the assignee
                String strCurrentUser = tools.findPage("pxRequestor")
                        .getString(".pyUserIdentifier");
                String strAssignee = tools.findPage("newAssignPage").getString(
                        ".pxAssignedOperatorID");
                if (!strCurrentUser.equals(strAssignee)) {
                    StringMap notificationKeys = new HashStringMap();
                    notificationKeys.putString("pxObjClass",
                            "Rule-Obj-Activity");
                    notificationKeys.putString("pyClassName", "Work-");
                    notificationKeys.putString("pyActivityName",
                            "pzCaseNotification");
                    try {
                        tools.doActivity(notificationKeys, tools.getStepPage(),
                                notificationParams);
                    } catch (com.pega.pegarules.pub.generator.RuleNotFoundException ex) {
                        tools.getStepStatus().backoutWorst();
                    }
                }
            }
        }
        LogTraceEvent(mRuleSet, mFlowHandle, flowName + " - " + taskName,
                "Flow Idle", "", mFlowHandle, taskName);
        flowIdle = true;
    }

    private boolean IsConnectorFlowAction_AssignmentSF3_circum0(String strAction) {
        if (strAction.equals("StepC"))
            return true;
        return false;
    }

    // TaskFlowActions returns false if none of the actions could be added to the assignment
    private boolean TaskFlowActions_AssignmentSF3_circum0(
            ClipboardPage assignPage) {
        boolean isFirstAction = true;
        if (AddFlowAction("StepC", "FlowAction", isFirstAction, false))
            isFirstAction = false;
        return !isFirstAction;
    }

    private void TaskConnectors_AssignmentSF3_circum0() {
        bMoreThanAlwaysInDraftMode = false;
        String strConnectorType = "";
        String strConnectorValue = "";
        if (CheckIfFlowHasEnded())
            return;
        else if (!pyNextFlowStep.equals(currentFlow
                .getString(".pyNextFlowStep")))
            // the flow has jumped to a (different) ticket, just abort this run
            return;

        // Evaluating the connectors (in order of likelihood)
        strConnectorType = "STATUS";
        strConnectorValue = "StepC";
        if (EvaluateConnector(strConnectorType, strConnectorValue, interestPage)) {
            // Trace Connector event
            LogTraceEvent(mRuleSet, "StepC", flowName, "Flow Connector", "",
                    mFlowHandle, "");

            // Perform listed Property-Sets

            // Now set the nextFlowStepToRun to this task
            nextFlowStepToRun = "SubProcessSF1";
            return;
        } else if (connectorFlowProblem == true)
            return;

        // Reaching this point means none of the connectors could be taken.
        StuckFlow();
    }

    private void LeaveContext_circum0(String contextID) {
        currentFlow.getProperty(".pyContexts").remove(contextID);
    }

    private boolean[] PopulateGlobalFlowActions_circum0(boolean isFirstAction,
            boolean isFirstLocalAction) {
        // Populate the common global (flow-wide) actions for this assignment; default to placing at bottom
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    private void PopulateFlowActions_circum0(String pyLastFlowStep,
            String strAssignmentPageName) {
        // First find the assignment page
        if (strAssignmentPageName == null || strAssignmentPageName.equals(""))
            strAssignmentPageName = "newAssignPage";
        ClipboardPage pg_newAssignPage = tools.findPage(strAssignmentPageName);
        if (pg_newAssignPage == null) {
            // We were unable to find the assignment page; abort.
            workPage.addMessage("The assignment page could not be found.");
            return;
        }

        // Next we need to remove any pre-existing actions
        pg_newAssignPage.getProperty(".pyAssignActions").removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultActionIndex")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskStatus")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskHTML")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskValue")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskInstructions")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskInstructionsCaption")
                .removeFromClipboard();
        pg_newAssignPage.getProperty(".pyDefaultTaskNextAssignment")
                .removeFromClipboard();
        boolean isFirstAction = true;
        boolean isFirstLocalAction = true;

        // Now populate the flow and local actions for this assignment
        if (pyLastFlowStep.equals("AssignmentSF4")) {
            if (TaskFlowActions_AssignmentSF4_circum0(pg_newAssignPage))
                isFirstAction = false;
            else
                // If none of the connector flow actions could be added, we will
                // (a) not show the separator bar (set isFirstLocalAction to false)
                // (b) show the first local action as the default flow action (isFirstAction is already true)
                isFirstLocalAction = false;
            boolean[] retParams;
            retParams = TaskLocalActions_AssignmentSF4_circum0(
                    pg_newAssignPage, isFirstAction, isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateGlobalFlowActions_circum0(isFirstAction,
                    isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateCaseFlowActions(isFirstAction,
                    isFirstLocalAction, false);
            // Expression: TreeNavigation7
            try {
                String formName = "TreeNavigation7";
                if (formName != null && formName.length() > 0) {
                    pg_newAssignPage.putString(".pxFormName", formName);
                }
            } catch (java.lang.IllegalArgumentException e) {
                if (!bDraftMode) {
                    ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    return;
                }
            }
        } else if (pyLastFlowStep.equals("AssignmentSF1")) {
            if (TaskFlowActions_AssignmentSF1_circum0(pg_newAssignPage))
                isFirstAction = false;
            else
                // If none of the connector flow actions could be added, we will
                // (a) not show the separator bar (set isFirstLocalAction to false)
                // (b) show the first local action as the default flow action (isFirstAction is already true)
                isFirstLocalAction = false;
            boolean[] retParams;
            retParams = TaskLocalActions_AssignmentSF1_circum0(
                    pg_newAssignPage, isFirstAction, isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateGlobalFlowActions_circum0(isFirstAction,
                    isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateCaseFlowActions(isFirstAction,
                    isFirstLocalAction, false);
            // Expression: TreeNavigation7
            try {
                String formName = "TreeNavigation7";
                if (formName != null && formName.length() > 0) {
                    pg_newAssignPage.putString(".pxFormName", formName);
                }
            } catch (java.lang.IllegalArgumentException e) {
                if (!bDraftMode) {
                    ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    return;
                }
            }
        } else if (pyLastFlowStep.equals("AssignmentSF2")) {
            if (TaskFlowActions_AssignmentSF2_circum0(pg_newAssignPage))
                isFirstAction = false;
            else
                // If none of the connector flow actions could be added, we will
                // (a) not show the separator bar (set isFirstLocalAction to false)
                // (b) show the first local action as the default flow action (isFirstAction is already true)
                isFirstLocalAction = false;
            boolean[] retParams;
            retParams = TaskLocalActions_AssignmentSF2_circum0(
                    pg_newAssignPage, isFirstAction, isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateGlobalFlowActions_circum0(isFirstAction,
                    isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateCaseFlowActions(isFirstAction,
                    isFirstLocalAction, false);
            // Expression: TreeNavigation7
            try {
                String formName = "TreeNavigation7";
                if (formName != null && formName.length() > 0) {
                    pg_newAssignPage.putString(".pxFormName", formName);
                }
            } catch (java.lang.IllegalArgumentException e) {
                if (!bDraftMode) {
                    ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    return;
                }
            }
        } else if (pyLastFlowStep.equals("AssignmentSF3")) {
            if (TaskFlowActions_AssignmentSF3_circum0(pg_newAssignPage))
                isFirstAction = false;
            else
                // If none of the connector flow actions could be added, we will
                // (a) not show the separator bar (set isFirstLocalAction to false)
                // (b) show the first local action as the default flow action (isFirstAction is already true)
                isFirstLocalAction = false;
            boolean[] retParams;
            retParams = TaskLocalActions_AssignmentSF3_circum0(
                    pg_newAssignPage, isFirstAction, isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateGlobalFlowActions_circum0(isFirstAction,
                    isFirstLocalAction);
            isFirstAction = retParams[0];
            isFirstLocalAction = retParams[1];
            retParams = PopulateCaseFlowActions(isFirstAction,
                    isFirstLocalAction, false);
            // Expression: TreeNavigation7
            try {
                String formName = "TreeNavigation7";
                if (formName != null && formName.length() > 0) {
                    pg_newAssignPage.putString(".pxFormName", formName);
                }
            } catch (java.lang.IllegalArgumentException e) {
                if (!bDraftMode) {
                    ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    return;
                }
            }
        } else
            currentFlow.getProperty(".pyLastFlowStep").addMessage(
                    "Flow_AssignShapeMissing\t" + pyLastFlowStep + "\t"
                            + currentFlow.getString(".pyLastFlowStepLabel"),
                    "pyMessageLabel");

        pega_processengine_flowutilities.pzMergeLocalActions(pg_newAssignPage,
                tools);
    }

    private void GetCoordinates_circum0(String pageName) {
        // Create a Rule-Obj-Flow page
        ClipboardPage pgFlow = tools.createPage("Rule-Obj-Flow", pageName);
        pgFlow.putString("pyFlowType", "CreateForm_Default");
        pgFlow.putString("pyClassName", "OOO-PGB-Work-Demo");
        pgFlow.putString("pyVisioScaleFactor", "100");
        pgFlow.putString("pzInsKey", mFlowHandle);
        pgFlow.putString("pyVisioJpegName",
                "ooo_pgb_work_democreateform_default20211127t110437_429_gmt.jpg");
        pgFlow.putString(
                "pyVisioRFB",
                "RULE-FILE-BINARY WEBWB OOO_PGB_WORK_DEMOCREATEFORM_DEFAULT20211127T110437_429_GMT!JPG #20211127T110437.424 GMT");
        pgFlow.putString("pyCropBoundRight", "");
        pgFlow.putString("pyCropBoundBottom", "");
        pgFlow.putString("pyCropBoundTop", "");
        pgFlow.putString("pyCropBoundLeft", "");
        pgFlow.putString("pyJpegScaleX", "");
        pgFlow.putString("pyJpegScaleY", "");

        ClipboardPage pgConnector;
        ClipboardProperty cpConnectors = pgFlow
                .getProperty(".pyModelProcess.pyConnectors");
        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition4");
        pgConnector.putString("pyMOName", "[Always]");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition4", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition3");
        pgConnector.putString("pyMOName", "");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition3", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition2");
        pgConnector.putString("pyMOName", "[Always]");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition2", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition1");
        pgConnector.putString("pyMOName", "");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition1", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition8");
        pgConnector.putString("pyMOName", "[Flow Action]");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition8", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition7");
        pgConnector.putString("pyMOName", "Not True");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition7", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition5");
        pgConnector.putString("pyMOName", "true");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition5", pgConnector);

        pgConnector = tools.createPage("Data-MO-Connector-Transition", "");
        pgConnector.putString("pyMOId", "Transition9");
        pgConnector.putString("pyMOName", "[Flow Action]");
        pgConnector.putString("pyCoordX", "");
        pgConnector.putString("pyCoordY", "");
        pgConnector.putString("pyWidth", "");
        pgConnector.putString("pyHeight", "");
        cpConnectors.add("Transition9", pgConnector);

        ClipboardPage pgShape;
        ClipboardProperty cpShapes = pgFlow
                .getProperty(".pyModelProcess.pyShapes");
        pgShape = tools
                .createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgShape.putString("pyMOId", "AssignmentSF4");
        pgShape.putString("pyMOName", "Step D");
        pgShape.putString("pyCoordX", "1.16");
        pgShape.putString("pyCoordY", "1.256");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("AssignmentSF4", pgShape);

        pgShape = tools.createPage("Data-MO-Gateway-Decision", "");
        pgShape.putString("pyMOId", "Decision1");
        pgShape.putString("pyMOName", "Selected?");
        pgShape.putString("pyCoordX", "1.16");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("Decision1", pgShape);

        pgShape = tools.createPage("Data-MO-Event-End", "");
        pgShape.putString("pyMOId", "END66");
        pgShape.putString("pyMOName", "");
        pgShape.putString("pyCoordX", "5.472");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.48");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("END66", pgShape);

        pgShape = tools.createPage(
                "Data-MO-Activity-SubProcess-ScreenflowProcess", "");
        pgShape.putString("pyMOId", "SubProcessSF1");
        pgShape.putString("pyMOName", "MySubFlow");
        pgShape.putString("pyCoordX", "3.992");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("SubProcessSF1", pgShape);

        pgShape = tools.createPage("Data-MO-Event-Start-StartScreenFlow", "");
        pgShape.putString("pyMOId", "Start62");
        pgShape.putString("pyMOName", "");
        pgShape.putString("pyCoordX", "-3.136");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.48");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("Start62", pgShape);

        pgShape = tools
                .createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgShape.putString("pyMOId", "AssignmentSF1");
        pgShape.putString("pyMOName", "Step A");
        pgShape.putString("pyCoordX", "-2.0640000000000005");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("AssignmentSF1", pgShape);

        pgShape = tools
                .createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgShape.putString("pyMOId", "AssignmentSF2");
        pgShape.putString("pyMOName", "Step B");
        pgShape.putString("pyCoordX", "-0.56");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("AssignmentSF2", pgShape);

        pgShape = tools
                .createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgShape.putString("pyMOId", "AssignmentSF3");
        pgShape.putString("pyMOName", "Step C");
        pgShape.putString("pyCoordX", "2.5520000000000005");
        pgShape.putString("pyCoordY", "0.24");
        pgShape.putString("pyWidth", "0.96");
        pgShape.putString("pyHeight", "0.48");
        cpShapes.add("AssignmentSF3", pgShape);

        ClipboardPage pgModifier;
        ClipboardProperty cpModifiers = pgFlow
                .getProperty(".pyModelProcess.pyModifiers");
        return;
    }

    private void GetOrderedFlowSteps_circum0(String stepPageName) {
        // Create a Code-Pega-List page
        ClipboardPage pgSteps = tools
                .createPage("Code-Pega-List", stepPageName);
        ClipboardProperty pxResults = pgSteps.getProperty(".pxResults");
        ClipboardPage pgStep;
        // Create a page to hold ordered flow entities
        // Call function to get ordered flow entities
        // Create a pxResult for each shape, exclude connectors (transitions)
        pgStep = tools.createPage("Data-MO-Event-Start-StartScreenFlow", "");
        pgStep.putString(".pyMOId", "Start62");
        pgStep.putString(".pyMOName", "");
        pgStep.putString(".pyImplementation", "WorkList");
        pgStep.putString(".pyCoordX", "-3.136");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "Start");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgStep.putString(".pyMOId", "AssignmentSF1");
        pgStep.putString(".pyMOName", "Step A");
        pgStep.putString(".pyImplementation", "WorkList");
        pgStep.putString(".pyCoordX", "-2.0640000000000005");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "AssignmentSF");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgStep.putString(".pyMOId", "AssignmentSF2");
        pgStep.putString(".pyMOName", "Step B");
        pgStep.putString(".pyImplementation", "WorkList");
        pgStep.putString(".pyCoordX", "-0.56");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "AssignmentSF");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Gateway-Decision", "");
        pgStep.putString(".pyMOId", "Decision1");
        pgStep.putString(".pyMOName", "Selected?");
        pgStep.putString(".pyImplementation", "");
        pgStep.putString(".pyCoordX", "1.16");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "Decision");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgStep.putString(".pyMOId", "AssignmentSF3");
        pgStep.putString(".pyMOName", "Step C");
        pgStep.putString(".pyImplementation", "WorkList");
        pgStep.putString(".pyCoordX", "2.5520000000000005");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "AssignmentSF");
        pxResults.add(pgStep);
        pgStep = tools.createPage(
                "Data-MO-Activity-SubProcess-ScreenflowProcess", "");
        pgStep.putString(".pyMOId", "SubProcessSF1");
        pgStep.putString(".pyMOName", "MySubFlow");
        pgStep.putString(".pyImplementation", "MySubFlow");
        pgStep.putString(".pyCoordX", "3.992");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "SubProcessSF");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Event-End", "");
        pgStep.putString(".pyMOId", "END66");
        pgStep.putString(".pyMOName", "");
        pgStep.putString(".pyImplementation", "");
        pgStep.putString(".pyCoordX", "5.472");
        pgStep.putString(".pyCoordY", "0.24");
        pgStep.putString(".pyFromMODefName", "End");
        pxResults.add(pgStep);
        pgStep = tools.createPage("Data-MO-Activity-Assignment-WorkAction", "");
        pgStep.putString(".pyMOId", "AssignmentSF4");
        pgStep.putString(".pyMOName", "Step D");
        pgStep.putString(".pyImplementation", "WorkList");
        pgStep.putString(".pyCoordX", "1.16");
        pgStep.putString(".pyCoordY", "1.256");
        pgStep.putString(".pyFromMODefName", "AssignmentSF");
        pxResults.add(pgStep);
        pgSteps.getProperty(".pxResultCount").setValue(
                pgSteps.getProperty(".pxResults").size());
    }

    private void GetNavigationPath_circum0(String pageName,
            String parentFlowPath, boolean deferredLoad) {
        ClipboardPage newAssignPage = tools.findPage("newAssignPage");
        if (newAssignPage == null)
            return;
        ClipboardPage pgNavPath = null;
        ClipboardPage pgFlowData = tools.findPage("pyFlowData");
        if (pgFlowData == null) {
            // The pyFlowData page needs to be created
            pgFlowData = tools.createPage("Code-Flow-Navigation", "pyFlowData");
            pgNavPath = pgFlowData;
        } else if (pageName.equals("pyFlowData")) {
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
                            .equals(mFlowHandle) || !isSameIntPage) {
                // Either we've been instructed to rebuild it from scratch rather than dynamically update due to when rules, or it is an old page from a previous flow or previous split
                pgFlowData = tools.createPage("Code-Flow-Navigation",
                        "pyFlowData");
                pgNavPath = pgFlowData;
            } else {
                // The pyFlowData page already exists and it is for this flow; we merely need to update it
                pega_processengine_flowutilities.updateNavigationPath(pageName,
                        "", interestPage, newAssignPage);
                return;
            }
        } else {
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
        if (interestPage.isEmbedded()) {
            pgNavPath.putString("pyInterestPage", interestPage.getReference());
            workPage = interestPage.getTopLevelPage();
        } else {
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
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
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
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("AssignmentSF1", "true");
        } else {
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
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
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
        } else
            pgStep.putString("pyStatus", "future");
        pgStep.putString("pyDisabled", "false");
        pgStep.putString("pyFAProcessOnJump", "false");
        pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

        strFlowAction = "StepA";
        pgStep.putString("pyFlowAction", strFlowAction);
        if (!strFlowAction.equals("")) {
            ClipboardPage pg = null;
            java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
            while (iter_pyFlowActions.hasNext()) {
                ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                        .next();
                ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
                if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference())) {
                    pg = pg_pyFlowAction;
                    break;
                }
            }

            if (pg == null) {
                pg = tools.createPage("Embed-Action", "");
                pg.putString("pyInterestPage", interestPage.getReference());
                pg.getProperty("pyActions").add(strFlowAction);
                cp_pyFlowActions.add(pg);
            } else
                pg.getProperty("pyActions").add(strFlowAction);
        }

        // *****************************************************************************
        // We need to determine if this connector Transition1 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("AssignmentSF1");
        toShapeValue = (String) hashShapes.get("AssignmentSF2");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
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
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("AssignmentSF2", "true");
        } else {
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
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
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
        } else
            pgStep.putString("pyStatus", "future");
        pgStep.putString("pyDisabled", "false");
        pgStep.putString("pyFAProcessOnJump", "false");
        pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

        strFlowAction = "StepB";
        pgStep.putString("pyFlowAction", strFlowAction);
        if (!strFlowAction.equals("")) {
            ClipboardPage pg = null;
            java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
            while (iter_pyFlowActions.hasNext()) {
                ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                        .next();
                ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
                if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference())) {
                    pg = pg_pyFlowAction;
                    break;
                }
            }

            if (pg == null) {
                pg = tools.createPage("Embed-Action", "");
                pg.putString("pyInterestPage", interestPage.getReference());
                pg.getProperty("pyActions").add(strFlowAction);
                cp_pyFlowActions.add(pg);
            } else
                pg.getProperty("pyActions").add(strFlowAction);
        }

        // *****************************************************************************
        // We need to determine if this connector Transition3 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("AssignmentSF2");
        toShapeValue = (String) hashShapes.get("Decision1");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
            // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
            newHashShapeValue = "n";
        }
        // Now concatenate this connector's value onto the destination shape's hash
        if (toShapeValue != null)
            newHashShapeValue = toShapeValue + newHashShapeValue;
        hashShapes.put("Decision1", newHashShapeValue);

        // *****************************************************************************
        // Examining step "Decision1"
        bThisShapeIsDisplayable = false;

        // Now determine whether this shape lies down a valid path
        // At least one connector leading into this shape must have set a 'y' in the hash entry
        hashShapeValue = (String) hashShapes.get("Decision1");
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("Decision1", "true");
        } else {
            bThisShapeIsDisplayable = false;
            hashShapes.put("Decision1", "false");
        }
        // *****************************************************************************
        // We need to determine if this connector Transition5 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("Decision1");
        toShapeValue = (String) hashShapes.get("AssignmentSF3");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                pgNavPath.getProperty("pyAlwaysRebuild").setValue(true);
                if (EvaluateConnector("WHEN", "IsSelected", interestPage)) {
                    newHashShapeValue = "y";

                    // concatenate 'taken' to the origin shape's hashmap value to denote that an exclusive path has now been taken
                    hashShapes.put("Decision1", fromShapeValue + "taken");
                } else
                    newHashShapeValue = "n";
            }
        } else {
            // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
            newHashShapeValue = "n";
        }
        // Now concatenate this connector's value onto the destination shape's hash
        if (toShapeValue != null)
            newHashShapeValue = toShapeValue + newHashShapeValue;
        hashShapes.put("AssignmentSF3", newHashShapeValue);

        // *****************************************************************************
        // Examining step "AssignmentSF3"
        bThisShapeIsDisplayable = true;

        // Now determine whether this shape lies down a valid path
        // At least one connector leading into this shape must have set a 'y' in the hash entry
        hashShapeValue = (String) hashShapes.get("AssignmentSF3");
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("AssignmentSF3", "true");
        } else {
            bThisShapeIsDisplayable = false;
            hashShapes.put("AssignmentSF3", "false");
        }
        bGetSubflowInfo = true;
        subFlowPrimaryPageRef = null; // default back to null
        pgStep = pgNavPath.getProperty(".pySteps(<APPEND>)").getPageValue();
        pgStep.putString("pyFlowStep", "AssignmentSF3");
        pgStep.putString("pyStepLabel", "Step C");
        pgStep.putString("pyStepType", "ASSIGNMENT");
        bIsInFlowPath = pega_procom_breadcrumbtrail.isInFlowPath2(tools,
                "AssignmentSF3", "CreateForm_Default", parentFlowPath,
                flowNameToSearchForTaskIn, interestPage.getTopLevelPage());
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
                bFoundFlowPath = true;
                flowNameToSearchForTaskIn = tools
                        .getParamValue("FoundInFlowName");
            }
            if (newAssignPage.getString("pxTaskName").equals("AssignmentSF3")
                    && newAssignPage.getString("pyFlowType").equals(
                            "CreateForm_Default")
                    && newAssignPage.getString("pyParentFlowPath").equals(
                            parentFlowPath))
                pgStep.putString("pyStatus", "current");
            else
                pgStep.putString("pyStatus", "success");
        } else
            pgStep.putString("pyStatus", "future");
        pgStep.putString("pyDisabled", "false");
        pgStep.putString("pyFAProcessOnJump", "false");
        pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

        strFlowAction = "StepC";
        pgStep.putString("pyFlowAction", strFlowAction);
        if (!strFlowAction.equals("")) {
            ClipboardPage pg = null;
            java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
            while (iter_pyFlowActions.hasNext()) {
                ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                        .next();
                ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
                if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference())) {
                    pg = pg_pyFlowAction;
                    break;
                }
            }

            if (pg == null) {
                pg = tools.createPage("Embed-Action", "");
                pg.putString("pyInterestPage", interestPage.getReference());
                pg.getProperty("pyActions").add(strFlowAction);
                cp_pyFlowActions.add(pg);
            } else
                pg.getProperty("pyActions").add(strFlowAction);
        }

        // *****************************************************************************
        // We need to determine if this connector Transition8 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("AssignmentSF3");
        toShapeValue = (String) hashShapes.get("SubProcessSF1");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
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
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("SubProcessSF1", "true");
        } else {
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
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
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
        } else
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
        else {
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
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
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
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("END66", "true");
        } else {
            bThisShapeIsDisplayable = false;
            hashShapes.put("END66", "false");
        }

        if (((String) hashShapes.get("END66")).equals("true")) {
            // The last shape has no exiting connectors and is on a valid path; mark all the shapes leading to it as ending shapes.
            feedingShapes = new java.util.Vector();
            itSteps = cpSteps.iterator();
            while (itSteps.hasNext()) {
                ClipboardProperty cpStep = (ClipboardProperty) itSteps.next();
                String strStep = cpStep.getPageValue()
                        .getProperty(".pyFlowStep").getStringValue();
                if (feedingShapes.contains(strStep)) {
                    cpStep.getPageValue().getProperty(".pyIsEndingShape")
                            .setValue(true);
                }
            }
            if (cpSteps.size() > 0)
                cpSteps.getPropertyValue(cpSteps.size())
                        .getProperty(".pyIsEndingShape").setValue(true);
        }

        // *****************************************************************************
        // We need to determine if this connector Transition7 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("Decision1");
        toShapeValue = (String) hashShapes.get("AssignmentSF4");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
            // If the origin shape is invalid, then this connector is too; no need to check prior sibling connectors or Whens
            newHashShapeValue = "n";
        }
        // Now concatenate this connector's value onto the destination shape's hash
        if (toShapeValue != null)
            newHashShapeValue = toShapeValue + newHashShapeValue;
        hashShapes.put("AssignmentSF4", newHashShapeValue);

        // *****************************************************************************
        // Examining step "AssignmentSF4"
        bThisShapeIsDisplayable = true;

        // Now determine whether this shape lies down a valid path
        // At least one connector leading into this shape must have set a 'y' in the hash entry
        hashShapeValue = (String) hashShapes.get("AssignmentSF4");
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("AssignmentSF4", "true");
        } else {
            bThisShapeIsDisplayable = false;
            hashShapes.put("AssignmentSF4", "false");
        }
        bGetSubflowInfo = true;
        subFlowPrimaryPageRef = null; // default back to null
        pgStep = pgNavPath.getProperty(".pySteps(<APPEND>)").getPageValue();
        pgStep.putString("pyFlowStep", "AssignmentSF4");
        pgStep.putString("pyStepLabel", "Step D");
        pgStep.putString("pyStepType", "ASSIGNMENT");
        bIsInFlowPath = pega_procom_breadcrumbtrail.isInFlowPath2(tools,
                "AssignmentSF4", "CreateForm_Default", parentFlowPath,
                flowNameToSearchForTaskIn, interestPage.getTopLevelPage());
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
                bFoundFlowPath = true;
                flowNameToSearchForTaskIn = tools
                        .getParamValue("FoundInFlowName");
            }
            if (newAssignPage.getString("pxTaskName").equals("AssignmentSF4")
                    && newAssignPage.getString("pyFlowType").equals(
                            "CreateForm_Default")
                    && newAssignPage.getString("pyParentFlowPath").equals(
                            parentFlowPath))
                pgStep.putString("pyStatus", "current");
            else
                pgStep.putString("pyStatus", "success");
        } else
            pgStep.putString("pyStatus", "future");
        pgStep.putString("pyDisabled", "false");
        pgStep.putString("pyFAProcessOnJump", "false");
        pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

        strFlowAction = "ActionStub";
        pgStep.putString("pyFlowAction", strFlowAction);
        if (!strFlowAction.equals("")) {
            ClipboardPage pg = null;
            java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
            while (iter_pyFlowActions.hasNext()) {
                ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                        .next();
                ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
                if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference())) {
                    pg = pg_pyFlowAction;
                    break;
                }
            }

            if (pg == null) {
                pg = tools.createPage("Embed-Action", "");
                pg.putString("pyInterestPage", interestPage.getReference());
                pg.getProperty("pyActions").add(strFlowAction);
                cp_pyFlowActions.add(pg);
            } else
                pg.getProperty("pyActions").add(strFlowAction);
        }

        // *****************************************************************************
        // We need to determine if this connector Transition9 comes from a valid path
        // First we check if the connector's origin shape is valid
        fromShapeValue = (String) hashShapes.get("AssignmentSF4");
        toShapeValue = (String) hashShapes.get("AssignmentSF1");
        if (fromShapeValue != null && fromShapeValue.indexOf("true") > -1) {
            // Next we need to check if a higher likelihood connector hasn't already been taken
            if (fromShapeValue.indexOf("taken") > -1) {
                // A higher likelihood sibling's path has already been taken, so this connector's path is invalid
                newHashShapeValue = "n";
            } else {
                // This connector doesn't have a conditional expression associated with it, so it is valid
                newHashShapeValue = "y";
            }
        } else {
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
        if (hashShapeValue != null && hashShapeValue.indexOf("y") > -1) {
            // It is easier for checking the connectors to replace the 'y' with 'true'
            hashShapes.put("AssignmentSF1", "true");
        } else {
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
        if (bIsInFlowPath) {
            if (!bFoundFlowPath) {
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
        } else
            pgStep.putString("pyStatus", "future");
        pgStep.putString("pyDisabled", "false");
        pgStep.putString("pyFAProcessOnJump", "false");
        pgStep.putString("pyDisplay", Boolean.toString(bThisShapeIsDisplayable));

        strFlowAction = "StepA";
        pgStep.putString("pyFlowAction", strFlowAction);
        if (!strFlowAction.equals("")) {
            ClipboardPage pg = null;
            java.util.Iterator iter_pyFlowActions = cp_pyFlowActions.iterator();
            while (iter_pyFlowActions.hasNext()) {
                ClipboardProperty cp_pyFlowAction = (ClipboardProperty) iter_pyFlowActions
                        .next();
                ClipboardPage pg_pyFlowAction = cp_pyFlowAction.getPageValue();
                if (pg_pyFlowAction.getString("pyInterestPage").equals(
                        interestPage.getReference())) {
                    pg = pg_pyFlowAction;
                    break;
                }
            }

            if (pg == null) {
                pg = tools.createPage("Embed-Action", "");
                pg.putString("pyInterestPage", interestPage.getReference());
                pg.getProperty("pyActions").add(strFlowAction);
                cp_pyFlowActions.add(pg);
            } else
                pg.getProperty("pyActions").add(strFlowAction);
        }

        // Now we iterate through pySteps and set pxNodeCount
        int nodecount = cpSteps.size();
        java.util.Iterator iter = cpSteps.iterator();
        while (iter.hasNext()) {
            ClipboardProperty stepRef = (ClipboardProperty) iter.next();
            ClipboardPage pg_Step = stepRef.getPageValue();
            String steptype = pg_Step.getString(".pyStepType");
            if (steptype.equals("FLOW") || steptype.equals("SPLITFOREACH"))
                nodecount += pg_Step.getProperty(".pyFlowData.pxNodeCount")
                        .toInteger();
        }
        cpNodeCount.setValue(nodecount);
    }

    private boolean CheckFlowSecurity_circum0() {
        boolean hasSecurity = false;
        String primaryPageClassName = null;
        if (tools.getPrimaryPage() != null)
            primaryPageClassName = tools.getPrimaryPage().getString(
                    "pxObjClass");
        if (primaryPageClassName == null) {
            primaryPageClassName = "OOO-PGB-Work-Demo";
        }

        // All privilege checks complete, at least one needed to be true else implicit privilege check is done
        boolean isAllowedAccess = false;
        if (!hasSecurity
                && tools.getAuthorizationHandle()
                        .havePrivilege(
                                tools,
                                "AM:RULE-OBJ-FLOW:OOO-PGB-WORK-DEMO!CREATEFORM_DEFAULT",
                                primaryPageClassName, interestPage))
            hasSecurity = true;
        if (!hasSecurity) {
            if (tools.getAuthorizationHandle().havePrivilege(tools,
                    "AM:RULE-OBJ-FLOW:OOO-PGB-WORK-DEMO!CREATEFORM_DEFAULT",
                    primaryPageClassName, interestPage, true))
                isAllowedAccess = true;
            else
                return false;
        }
        if (isAllowedAccess && !hasSecurity) {
            com.pega.pegarules.priv.tracer.RuleTraceInfo ruleTraceInfo = new com.pega.pegarules.priv.tracer.RuleTraceInfo(
                    "RULE-OBJ-FLOW OOO-PGB-WORK-DEMO CREATEFORM_DEFAULT #20211127T110301.884 GMT",
                    "CreateForm_Default", "PGB", "", "20211128T112343.957 GMT");
            if (!tools.hasImplicitPrivilege(ruleTraceInfo)) {
                return false;
            }
        }

        return true;
    }

    private String mRuleSet;
    private String mFlowHandle;
    private com.pega.pegarules.priv.tracer.RuleTraceInfo mTraceInfo;

    private ClipboardPage workPage;
    private String workPageName;
    private String workPageHandle;
    private boolean workPageCorrupted = false;
    private String workUpdateDateTime;
    private ClipboardPage interestPage;
    private ClipboardPage myStepPage;
    private ClipboardProperty currentFlowRef;
    private ClipboardPage currentFlow;
    private String flowType;
    private String pyNextFlowStep;
    private StringMap callerParameterPage;
    private ClipboardPage pyFlowParametersPage;
    private String nextFlowStepToRun = "";
    private String lastFlowStepRun = "";
    private java.util.Hashtable taskCalledCount = new java.util.Hashtable();
    private java.util.Hashtable endingTasks = new java.util.Hashtable();
    private boolean flowHasEnded = false;
    private boolean flowIdle = false;
    private String flowName = "";
    private boolean connectorFlowProblem = false;
    private boolean RethrowConnectionProblem = false;
    private boolean InRetryConnection = false;
    private boolean isTemporaryObject = false;
    private boolean newObject = false;
    private boolean buildFlowPage = false;
    private boolean bInProblemFlow = false;
    private boolean bDraftMode = false;
    private boolean bCalledNonExistentRule = false;
    private boolean bMoreThanAlwaysInDraftMode = false;
    private String currentSFEHeaderLabel = "";
    private String currentSFESubscript = "";

    private void LogTraceMessage(String message) {
        LogTraceEvent(mRuleSet, message, flowName, "Flow Message", "",
                mFlowHandle, "");
    }

    private void LogTraceEvent(String message, String stepMethod,
            String eventType, String status, String taskName) {
        LogTraceEvent(mRuleSet, message, stepMethod, eventType, status,
                mFlowHandle, taskName);
    }

    private void LogTraceEvent(String ruleSet, String rulekey,
            String stepMethod, String eventType, String status,
            String flowHandle, String taskName) {
        HashStringMap optionalProperties = new HashStringMap();
        optionalProperties.putString("FlowHandle", flowHandle);
        optionalProperties.putString("taskName", taskName);

        pega.logTraceEvent("Flow", eventType, status, mTraceInfo, rulekey,
                stepMethod, false, tools.getPrimaryPage(), tools
                        .getStackFrame().getParameterPage(), optionalProperties);
    }

    private boolean CheckIfFlowHasEnded() {
        return CheckIfFlowHasEnded(true);
    }

    private boolean CheckIfFlowHasEnded(boolean checkIfEndingTask) {
        // If the flag is already set, we know the flow has reached a FLOWEND
        if (flowHasEnded == true)
            return true;

        // Next we check to see if the work page was tampered with or not.
        // Unfortunately, for the accelerator to work, we must allow the page
        // to be swapped out.  But we can never allow it to be reopened from the db.
        workPageName = workPage.getName();
        if (workPageName.length() > 0 && workPageCorrupted == false) {
            ClipboardPage testWorkPage = tools.findPage(workPageName);
            if (testWorkPage == null) {
                // The workpage has been removed from the clipboard directory.  It now only
                // exists through the workPage variable's existence.
                LogTraceEvent(mRuleSet, "Warning: the flow's work page "
                        + workPageName
                        + " has been removed from the clipboard.", flowName,
                        "Flow Message", "", mFlowHandle, "CheckIfFlowEnded");
            } else if (workPageHandle.length() > 0) {
                // We must make sure the named page on the clipboard is still the same one
                String tempHandle = "";
                try {
                    tempHandle = tools.getDatabase().getHandle(testWorkPage);
                } catch (DatabaseException de) {
                }
                if (!workPageHandle.equals(tempHandle)) {
                    // The application has switched the work page.
                    // Find out if the original is gone or is an orphaned page.
                    String currentWorkHandle = "";
                    try {
                        currentWorkHandle = tools.getDatabase().getHandle(
                                workPage);
                    } catch (DatabaseException de) {
                    }
                    if (currentWorkHandle.equals(workPageHandle)) {
                        // The work page is now orphaned on the clipboard
                        LogTraceEvent(mRuleSet,
                                "Warning: the flow's work page is now orphaned from the clipboard page "
                                        + workPageName + ".", flowName,
                                "Flow Message", "", mFlowHandle,
                                "CheckIfFlowEnded");
                    } else {
                        // The flow is now pointing to a different work object, so further
                        // steps are using a different primary page.
                        LogTraceEvent(mRuleSet,
                                "Warning: Aborting flow, the work page "
                                        + workPageName
                                        + " has been switched from "
                                        + workPageHandle + " to " + tempHandle
                                        + ".", flowName, "Flow Message", "",
                                mFlowHandle, "CheckIfFlowEnded");
                        workPageCorrupted = true;
                        flowHasEnded = true;
                        return true;
                    }
                } else if (workUpdateDateTime.length() > 0) {
                    String testUpdateDateTime = testWorkPage
                            .getString(".pxUpdateDateTime");
                    if (testUpdateDateTime.length() == 0
                            || (!workUpdateDateTime.equals(testUpdateDateTime) && pega_rules_datetime
                                    .CompareDates(workUpdateDateTime,
                                            testUpdateDateTime))) {
                        LogTraceEvent(
                                mRuleSet,
                                "Warning: The work page "
                                        + workPageName
                                        + " has unexpectedly been reverted to an older version.",
                                flowName, "Flow Message", "", mFlowHandle,
                                "CheckIfFlowEnded");
                    }
                }
            }
        }

        // Now we need to re-set the currentFlowRef variable, because
        // tickets can wreak havoc with it
        currentFlowRef = workPage.getIfPresent(".pxFlow(" + flowName + ")");
        if (currentFlowRef == null) {
            currentFlow = null;
            flowHasEnded = true;
            return true;
        } else
            // reset the currentFlow variable
            currentFlow = currentFlowRef.getPageValue();

        // Then there's the Flow-End method
        ClipboardProperty pxCurrentFlowEnd = tools.findPage("pxThread")
                .getProperty(".pxCurrentFlowEnd");
        if (pxCurrentFlowEnd.hasGoodValue()
                && pxCurrentFlowEnd.toBoolean() == true) {
            flowHasEnded = true;
            // Don't forget to reset the flag!
            pxCurrentFlowEnd.setValue(false);
            return true;
        }

        // Finally, we need to see if the last flow step is in the ending tasks hashtable
        if (checkIfEndingTask
                && endingTasks.contains(currentFlow
                        .getString(".pyLastFlowStep")) && !flowIdle) {
            flowHasEnded = true;
            return true;
        }

        return false;
    }

    private ParameterPage createNewParamsPage(String taskName) {
        ParameterPage newParamsPage = new ParameterPage();

        // First copy all properties from pyFlowParameters into the parameter page
        ClipboardProperty pyFlowParameters = currentFlow
                .getProperty(".pyFlowParameters");
        newParamsPage.putAll(pyFlowParameters.getPageValue());

        // Now copy the following additional parameters into newParamsPage
        newParamsPage.putString("TaskName", taskName);
        String pxObjClass = workPage.getClassName();
        newParamsPage.putString("ReferenceClass", pxObjClass);
        if (!isTemporaryObject) {
            try {
                newParamsPage.putString("ReferenceInsKey", tools.getDatabase()
                        .getHandle(workPage));
            } catch (com.pega.pegarules.pub.database.DatabaseException e) {
            }
            String insName = workPage.getString(".pxInsName");
            if (insName.length() == 0) {
                // v3 doesn't update pxInsName until DB.save(), so get here if necessary
                if (pxObjClass.length() > 0) {
                    ClassDefinition instanceClass = tools.getDatabase()
                            .getClassDef(pxObjClass);
                    if (instanceClass != null)
                        insName = tools.getDatabase().getInstanceName(workPage,
                                instanceClass);
                }
            }
            newParamsPage.putString("ReferenceInsName", insName);
        }
        newParamsPage.putString("ReferencePageName", workPage.getName());
        newParamsPage.putString("TimeFlowStarted",
                currentFlow.getString(".pxTimeFlowStarted"));
        newParamsPage.putString("InterestPage",
                currentFlow.getString(".pyFlowInterestPage"));
        newParamsPage.putString("InterestPageClass",
                interestPage.getClassName());
        newParamsPage.putString("FlowHasEnded", "N");
        newParamsPage.putString("flowType",
                currentFlow.getString(".pyFlowType"));
        newParamsPage.putString("IsInvestigative",
                currentFlow.getString(".pxIsInvestigative"));
        newParamsPage.putString("pyParentFlowPath",
                currentFlow.getString(".pyParentFlowPath"));
        newParamsPage.putString("pyTopLevelFlow",
                currentFlow.getString(".pyTopLevelFlow"));
        newParamsPage.putString("pyDeferCommit",
                currentFlow.getString(".pyDeferCommit"));
        newParamsPage.putString("pyDeferErrors",
                currentFlow.getString(".pyDeferErrors"));
        newParamsPage.putString("pyDraftMode",
                currentFlow.getString(".pyDraftMode"));

        // Now set stage information for the assignment
        String stageID;
        String stageLabel;
        String toplevelflow = currentFlow.getStringIfPresent(".pyTopLevelFlow");
        if (toplevelflow == null || toplevelflow.equals("")) {
            stageID = currentFlow.getStringIfPresent(".pyIssuedFromStage");
            stageLabel = currentFlow
                    .getStringIfPresent(".pyIssuedFromStageLabel");
        } else {
            ClipboardProperty cptoplevelflow = workPage.getProperty(".pxFlow("
                    + toplevelflow + ")");
            ClipboardPage pgTopLevelFlow = cptoplevelflow.getPageValue();
            stageID = pgTopLevelFlow.getStringIfPresent(".pyIssuedFromStage");
            stageLabel = pgTopLevelFlow
                    .getStringIfPresent(".pyIssuedFromStageLabel");
        }
        if (stageID == null)
            stageID = "";
        if (stageLabel == null)
            stageLabel = "";
        newParamsPage.putString("pyStageID", stageID);
        newParamsPage.putString("pyStageLabel", stageLabel);

        return newParamsPage;
    }

    private boolean RunTaskActivity(String taskName, String taskActivity,
            ClipboardPage useAsPrimPage, ParameterPage newParamsPage) {
        // First check for non-specified in draft mode
        if (bDraftMode && taskActivity.equals("")) {
            bCalledNonExistentRule = true;
            return true;
        }

        // Store the current step processed to make sure that it is the same after we invoke activity
        String strCurrentFlowStep = null;
        strCurrentFlowStep = (currentFlow != null ? currentFlow
                .getString("pyCurrentFlowStep") : null);
        // Run the Task activity
        try {
            newParamsPage.putString("CreateProblemFlow", "");
            StringMap activity = new HashStringMap();
            activity.putString("pxObjClass", "Rule-Obj-Activity");
            activity.putString("pyActivityName", taskActivity);
            if (newParamsPage.getString("pyActivityType") != null
                    && newParamsPage.getString("pyActivityType").equals(
                            "AUTOMATION")) {
                ClipboardPage tempPrimaryPage = tools.createPage(
                        newParamsPage.getString("pyAutomationAppliesTo"),
                        "tempPrimaryPage");
                newParamsPage.put("pyAutomationErrors",
                        useAsPrimPage.getProperty("pyAutomationErrorHolder"));
                activity.putString("pyClassName",
                        newParamsPage.getString("pyAutomationAppliesTo"));
                tools.doActivity(activity, tempPrimaryPage, newParamsPage);
                tempPrimaryPage.removeFromClipboard();
                String automationErrorPageName = useAsPrimPage
                        .getString("pyAutomationErrorHolder");
                ClipboardPage errorPage = tools
                        .findPage(automationErrorPageName);
                //oLog.infoForced(errorPage.getXML());
                if (!(errorPage.getString("pyErrorClassification").equals(""))) {
                    // All automation errors result in a problem flow
                    ProblemFlow(
                            errorPage
                                    .getString(".pyErrorDetails(1).pyLocalizedValue"),
                            taskName);
                    return false;
                }
            } else {
                activity.putString("pyClassName",
                        useAsPrimPage.getString(".pxObjClass"));
                tools.doActivity(activity, useAsPrimPage, newParamsPage);
            }

            // Applications can create problem flows by either setting the CreateProblemFlow
            // parameter or by throwing a PRAppRuntimeException
            String problemFlowMessage = newParamsPage
                    .getString("CreateProblemFlow");
            if (problemFlowMessage != null && problemFlowMessage.length() > 0) {
                newParamsPage.putString("CreateProblemFlow", "");
                ProblemFlow(problemFlowMessage, taskName);
                return false;
            }
        } catch (PRRuntimeError e) {
            if (e instanceof RuleNotFoundException && bDraftMode) {
                bCalledNonExistentRule = true;
                return true;
            } else {
                ProblemFlow(e, taskName);
                return false;
            }
        } catch (ConnectorException e) {
            // Check if the connection problem is already being handled
            if (RethrowConnectionProblem)
                throw e;
            // Check if the connection problem handler flow is specified
            String handlerFlow = ((ConnectorException) e).getHandler();
            if (handlerFlow != null && handlerFlow.length() > 0)
                ConnectionProblem(e.getClass().getName(), handlerFlow,
                        e.getMessage(), taskName);
            else
                ProblemFlow(e, taskName);
            return false;
        } catch (PRAppRuntimeException e) {
            if (e.getRuleSetName().equals("Pega-ProCom") && e.getReason() == 1)
                // No need for the stacktrace
                ProblemFlow(e.getMessage(), taskName);
            else
                ProblemFlow(e, taskName);
            return false;
        }

        catch (RuntimeException e) {
            ProblemFlow(e, taskName);
            return false;
        }

        // The activity may have raised a ticket which ended the flow
        if (CheckIfFlowHasEnded(false) == true)
            return false;
        //Check to see if the state of the flow has change out from under us 
        if (strCurrentFlowStep != null
                && !strCurrentFlowStep.equals(currentFlow
                        .getString("pyCurrentFlowStep"))) {
            LogTraceMessage("last flow step changed");
            return false; // Abort this chain as the state has changed
        }

        // No exceptions
        return true;
    }

    private boolean LaunchFlow(String pyDefineFlowOn, String taskName,
            String taskType, String issuedAs, String pyNewObjReference,
            String pyFlowInterestPage, ParameterPage newParamsPage,
            String flowType) {
        return LaunchFlow(pyDefineFlowOn, taskName, taskType, issuedAs,
                pyNewObjReference, pyFlowInterestPage, newParamsPage, flowType,
                false, "", PropertyInfo.MODE_PAGE);
    }

    private boolean LaunchFlow(String pyDefineFlowOn, String taskName,
            String taskType, String issuedAs, String pyNewObjReference,
            String pyFlowInterestPage, ParameterPage newParamsPage,
            String flowType, boolean bSaveFlowPath, String issuedSubscript) {
        return LaunchFlow(pyDefineFlowOn, taskName, taskType, issuedAs,
                pyNewObjReference, pyFlowInterestPage, newParamsPage, flowType,
                bSaveFlowPath, issuedSubscript, PropertyInfo.MODE_PAGE);
    }

    private boolean LaunchFlow(String pyDefineFlowOn, String taskName,
            String taskType, String issuedAs, String pyNewObjReference,
            String pyFlowInterestPage, ParameterPage newParamsPage,
            String flowType, boolean bSaveFlowPath, String issuedSubscript,
            char chMode) {
        if (taskType.equalsIgnoreCase("WKBRANCH")
                || (issuedAs != null && (issuedAs.equalsIgnoreCase("SPLIT_ALL")
                        || issuedAs.equalsIgnoreCase("SPLIT_ANY") || issuedAs
                            .equalsIgnoreCase("SPLIT_SOME")))) {
            boolean bDoNotSaveAssigns = workPage
                    .getBoolean("pyDoNotSaveAssignments");
            if (bDoNotSaveAssigns) {
                workPage.putString("pyDoNotSaveAssignments", "false");
            }
        }
        // First check for non-specified in draft mode
        if (bDraftMode && flowType.equals("")) {
            bCalledNonExistentRule = true;
            return true;
        }

        ClipboardPage useAsPrimPage = workPage;
        ClipboardPage intPage = null;
        String pyFlowInterestPageHandle = "";
        // Store the current step processed to make sure that it is the same after we invoke activity
        String strCurrentFlowStep = null;
        strCurrentFlowStep = (currentFlow != null ? currentFlow
                .getString("pyCurrentFlowStep") : null);

        if (pyDefineFlowOn.equalsIgnoreCase("DIFFERENT")) {
            // Here pyFlowInterestPage is a pagename for the different object,
            // and not the actual interest page of the new pxFlow page.
            // We do NOT support interest pages on different objects (i.e. embedded pages therein)
            // It is assumed here that at validation time, one of pyNewObjReference
            // and pyFlowInterestPage was determined to have a value.
            ClipboardPage diffPage = null;
            String diffKey = "";
            String insKey = "";
            String pageName = "";
            boolean isSelf = false;
            boolean isTempDiffObject = false;

            if (pyNewObjReference.length() > 0) {
                // the New object reference is a property on the interest page that has the key to the new page
                try {
                    ClipboardProperty newObjRef = interestPage
                            .getIfPresent(pyNewObjReference);
                    if (newObjRef == null) {
                        ProblemFlow(
                                "ProblemFlow_PropertyReferenceNotOnPrimaryPage\t"
                                        + pyNewObjReference, taskName);
                        return false;
                    }
                    insKey = newObjRef.getStringValue();
                } catch (PRRuntimeException e) {
                    ProblemFlow(e, taskName);
                    return false;
                }
            }

            if (pyFlowInterestPage.length() > 0) {
                pageName = pyFlowInterestPage;
                pyFlowInterestPage = "";
                diffPage = tools.findPage(pageName);
                if (diffPage != null) {

                    //Check if the specified page is a temporary object 
                    isTempDiffObject = Boolean.parseBoolean(diffPage
                            .getStringIfPresent("pyTemporaryObject"));

                    if (isTempDiffObject == false) {
                        try {
                            diffKey = tools.getDatabase().getHandle(diffPage);
                        } catch (DatabaseException f) {
                            ProblemFlow(f, taskName);
                            return false;
                        }

                        // Check if specified page is current work page (self)
                        isSelf = workPageHandle.equals(diffKey);

                        if (isSelf == false) {
                            if (insKey.length() > 0 && !diffKey.equals(insKey)) {
                                // We need to unlock the page before we overwrite it
                                newParamsPage.putString("workOnly", "true");
                                pega_rules_utilities.callActivity(diffPage,
                                        "WorkUnlock", newParamsPage);
                            } else if (!diffPage.getString("pzInsKey").equals(
                                    ""))
                                // Since pzInsKey isn't blank on this page, it has been saved
                                // to the database and is therefore a candidate for WorkLock.
                                insKey = diffKey;
                        }
                    }
                } else if (diffPage == null && insKey.length() == 0) {
                    ProblemFlow(
                            "ProblemFlow_InterestPageNotFound\t" + pageName,
                            taskName);
                    return false;
                }
            } else if (insKey.length() > 0) {
                // Search the clipboard for this page
                diffPage = tools.findPageByHandle(insKey);
                if (diffPage == null)
                    pageName = pega_rules_utilities
                            .getUniquePageName("pyNextObj");
                else
                    pageName = diffPage.getName();
            }

            else {
                ProblemFlow(
                        "ProblemFlow_InterestPageNotFound\t[page not specified]",
                        taskName);
                return false;
            }

            // Skip the locking if self or a temporary work object 
            if (isSelf == false && isTempDiffObject == false) {
                // Since we have a key, we must try to lock it
                if (diffPage == null) {
                    // Create a temporary work page for calling WorkLock
                    diffPage = tools.createPage("Work-", pageName);
                }

                newParamsPage.putString("workPage", pageName);
                newParamsPage.putString("workHandle", insKey);
                newParamsPage.putString("workOnly", "true");
                pega_rules_utilities.callActivity(diffPage, "WorkLock",
                        newParamsPage);
                if (newParamsPage.getAsInteger("SuccessIndicator") < 0) {
                    ProblemFlow(newParamsPage.getString("FailureInfo"),
                            taskName);
                    return false;
                }

                diffPage = tools.findPage(pageName);
            }
            // the new object becomes the primary object for the new flow - i.e., the
            // flow will actually live in the new object
            useAsPrimPage = diffPage;
            intPage = useAsPrimPage;
        } else {
            // CURRENT object then, it may have an interest page though
            if (pyFlowInterestPage.length() == 0)
                // Here's the tricky part.  The new flow may not have an interest page defined, but it needs to
                // use the interest page of the calling flow, if any
                pyFlowInterestPage = currentFlow
                        .getString(".pyFlowInterestPage");

            if (newObject == true)
                newParamsPage.putString("pyNewObject", "true");
        }

        // Generate a new name for the flow (flowtype is the default) and add it
        String newFlowName = pega_procom_flow.AddFlow(tools, useAsPrimPage,
                flowType, flowType, pyFlowInterestPage, currentFlow
                        .getProperty("pxIsInvestigative").toBoolean());
        if (newFlowName.equals("")) {
            ProblemFlow("ProblemFlow_AddFlowFail", taskName);
            return false;
        }

        // Save this new flow name in the newParamsPage
        newParamsPage.putString("flowName", newFlowName);

        ClipboardProperty flowProp = useAsPrimPage.getProperty(".pxFlow");
        ClipboardPage newFlow = flowProp.getProperty(newFlowName)
                .getPageValue();
        newFlow.putString("pySaveCompletedFlowPath",
                Boolean.toString(bSaveFlowPath));

        if (intPage == null) {
            // Now figure out the interest page of the flow.  If the flow is in a non work- class,
            // this is extremely important!  The interest page, if different from the work object, is
            // used as the primary page of the utility activities.  Otherwise, the interest page is
            // identical to the workPage
            intPage = pega_procom_flow
                    .GetInterestPage(tools, workPage, newFlow);
            if (intPage == null || intPage.isEmpty()) {
                String errMessage = "ProblemFlow_InterestPageNotFound\t"
                        + pyFlowInterestPage;
                ProblemFlow(errMessage, taskName);
                return false;
            }
        }

        // Fill up the new flow page
        if (taskType.equalsIgnoreCase("FLOW")
                || taskType.equalsIgnoreCase("SPLITFOREACH")) {
            // We will NOT set these properties for WKBRANCH (spinoff) type Task, because these
            // properties are used for reissuing the (waiting) calling flow when pyFirstRun = false
            if (!isTemporaryObject) {
                try {
                    newFlow.putString("pyIssuedFromObject", tools.getDatabase()
                            .getHandle(workPage));
                } catch (com.pega.pegarules.pub.database.DatabaseException e) {
                    ProblemFlow(e, taskName);
                    return false;
                }
            }
            newFlow.putString("pyIssuedFromFlow", flowName);
            newFlow.putString("pyIssuedFromClass", workPage.getClassName());
            newFlow.putString("pyIssuedAs", issuedAs);
            newFlow.putString("pyIssuedFromTask", taskName);
            newFlow.putString("pyIssuedFromPage", workPage.getName());
            newFlow.putString("pyIssuedFromSplitSubscript", issuedSubscript);
            String parentFlowPath = currentFlow.getString(".pyParentFlowPath");
            if (parentFlowPath.equals(""))
                newFlow.putString("pyParentFlowPath", taskName);
            else
                newFlow.putString("pyParentFlowPath", parentFlowPath + "/"
                        + taskName);
            if (!issuedSubscript.equals(""))
                newFlow.getProperty("pyParentFlowPath").setValue(
                        newFlow.getString("pyParentFlowPath") + "("
                                + issuedSubscript + ")");
            if (currentFlow.getString(".pyTopLevelFlow").equals(""))
                newFlow.putString("pyTopLevelFlow", flowName);
            else
                newFlow.putString("pyTopLevelFlow",
                        currentFlow.getString(".pyTopLevelFlow"));

            // Copy parameter pyPreviousOperator to child if set
            ClipboardProperty pyUsePreviousOperator = currentFlow
                    .getIfPresent(".pyUsePreviousOperator");
            if (pyUsePreviousOperator != null
                    && pyUsePreviousOperator.toBoolean() == true)
                newFlow.getProperty(".pyUsePreviousOperator").setValue(true);
        } else if (taskType.equalsIgnoreCase("WKBRANCH")) {
            // Even though this was a spinoff, it still helps to keep track of the spinning-off task
            newFlow.putString("pyIssuedFromTask", taskName);
        }

        if (buildFlowPage) {
            // We want to jump in to the new flow at a different spot, so don't launch.
            // Continue setup & return the newFlowName to the caller and exit.
            newFlow.putString(".pyFlowInterestPageClass",
                    intPage.getClassName());

            // Copy all the parameters from the parameter page into the flow parameter page
            newFlow.getProperty("pyFlowParameters").getPageValue()
                    .copyParameterValues(newParamsPage);
            callerParameterPage.putString("newFlowPageName", newFlowName);

        }
        ClipboardProperty cpJumpToSubscript = currentFlow
                .getIfPresent(".pyJumpToSubscript");
        if (buildFlowPage
                || (cpJumpToSubscript != null && !cpJumpToSubscript
                        .getStringValue().equals(""))) {
            // If the current shape is in the flowpath, we need to remove the completed flows
            // and if this is buildFlowPage mode, copy over the completed to the new
            ClipboardProperty cpFlowPath = currentFlow
                    .getProperty(".pyFlowPath");
            java.util.Iterator itFlowPath = cpFlowPath.iterator();
            while (itFlowPath.hasNext()) {
                ClipboardProperty cpFlowStep = (ClipboardProperty) itFlowPath
                        .next();
                ClipboardPage pgFlowStep = cpFlowStep.getPageValue();
                String strFlowStep = pgFlowStep.getString("pyFlowStep");
                if (strFlowStep.equals(taskName)) {
                    ClipboardProperty completedFlowPath = pgFlowStep
                            .getIfPresent("pyCompletedSubFlowPath");
                    if (completedFlowPath != null) {
                        // Keep in mind that if there is a pyJumpToSubscript set, we won't be here
                        newFlow.getProperty(".pyFlowPath").setValue(
                                completedFlowPath);
                        completedFlowPath.removeFromClipboard();
                    } else if (!issuedSubscript.equals("")) {
                        ClipboardProperty completedSplitPath = pgFlowStep
                                .getIfPresent("pyCompletedSplitForEachPath");
                        if (completedSplitPath != null) {
                            // We need to remove the completed split flows after the current one
                            if (chMode == PropertyInfo.MODE_PAGE_LIST) {
                                int plIdx = 0;
                                try {
                                    plIdx = new Integer(issuedSubscript)
                                            .intValue();
                                } catch (Exception e) {
                                }
                                if (plIdx > 0) {
                                    int size = completedSplitPath.size();
                                    for (int i = size; i > 0; i--) {
                                        ClipboardProperty completedSplitPathRef = completedSplitPath
                                                .getPropertyValue(i);
                                        int completedIdx = completedSplitPathRef
                                                .getProperty(
                                                        "pyCompletedSubFlowSubscript")
                                                .toInteger();
                                        if (completedIdx < plIdx)
                                            break;
                                        else if (completedIdx == plIdx) {
                                            if (buildFlowPage)
                                                newFlow.getProperty(
                                                        ".pyFlowPath")
                                                        .setValue(
                                                                completedSplitPathRef
                                                                        .getProperty(".pyCompletedSubFlowPath"));
                                            completedSplitPathRef
                                                    .removeFromClipboard();
                                            break;
                                        } else
                                            completedSplitPathRef
                                                    .removeFromClipboard();
                                    }
                                }
                            } else {
                                // It's a pagegroup
                                // We need to remove this particular subscript & subsequent ones from the completed flowpaths
                                java.util.Iterator splitIt = completedSplitPath
                                        .iterator();
                                while (splitIt.hasNext()) {
                                    ClipboardProperty completedSplitPathRef = (ClipboardProperty) splitIt
                                            .next();
                                    ClipboardPage splitPage = completedSplitPathRef
                                            .getPageValue();
                                    String splitSubscript = splitPage
                                            .getString(".pyCompletedSubFlowSubscript");
                                    if (splitSubscript.equals(issuedSubscript)) {
                                        if (buildFlowPage)
                                            newFlow.getProperty(".pyFlowPath")
                                                    .setValue(
                                                            splitPage
                                                                    .getProperty(".pyCompletedSubFlowPath"));
                                        completedSplitPathRef
                                                .removeFromClipboard();
                                    } else {
                                        // We need to check if the completed subscript comes after the issued subscript in
                                        // the pagegroup iteration order.  If so, remove it
                                        java.util.Iterator itPageGroup = intPage
                                                .getParentProperty()
                                                .getParentProperty().iterator();
                                        while (itPageGroup.hasNext()) {
                                            ClipboardProperty cpIntPageRef = (ClipboardProperty) itPageGroup
                                                    .next();
                                            ClipboardPage pgIntPage = cpIntPageRef
                                                    .getPageValue();
                                            String strSubscript = pgIntPage
                                                    .getSubscript();
                                            if (strSubscript
                                                    .equals(splitSubscript)) {
                                                // The subscript of this completed flow page comes before the current page
                                                break;
                                            } else if (strSubscript
                                                    .equals(issuedSubscript)) {
                                                // We've jumped back to before this subscript
                                                completedSplitPathRef
                                                        .removeFromClipboard();
                                                ClipboardProperty cpSubscriptsIteratedOver = currentFlow
                                                        .getProperty(".pySubscriptsIteratedOver");
                                                java.util.Iterator itSubscripts = cpSubscriptsIteratedOver
                                                        .iterator();
                                                while (itSubscripts.hasNext()) {
                                                    ClipboardProperty cpSubscript = (ClipboardProperty) itSubscripts
                                                            .next();
                                                    String strIteratedSubscriptValue = cpSubscript
                                                            .getStringValue();
                                                    if (strIteratedSubscriptValue
                                                            .equals(splitSubscript)) {
                                                        cpSubscript
                                                                .removeFromClipboard();
                                                        break;
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            if (completedSplitPath.size() == 0)
                                completedSplitPath.removeFromClipboard();
                        }
                    }
                    break;
                }
            }
        }
        if (buildFlowPage) {
            LogTraceMessage("Called in buildFlowPage mode; flow " + newFlowName
                    + " successfully added.");
            return true;
        }

        newParamsPage.putString("ReferencePageName", useAsPrimPage.getName());

        try {
            StringMap flow = new HashStringMap();
            flow.putString("pxObjClass", "Rule-Obj-Flow");
            flow.putString("pyClassName", intPage.getClassName());
            flow.putString("pyFlowType", flowType);
            tools.doActivity(flow, intPage, newParamsPage);
        } catch (RuntimeException e) {
            if (e instanceof PRAppRuntimeException
                    && InRetryConnection == false)
                throw e;
            else if (e instanceof PRAppRuntimeException
                    || e instanceof PRRuntimeException) {
                if (issuedAs.startsWith("SPLIT_")) {
                    // Reset pyFlowWaitCount to 0
                    currentFlow.getProperty("pyFlowWaitCount").setValue(0);

                    // Remove any other flows that were split off (so we can start fresh)
                    ClipboardProperty pxFlow = useAsPrimPage
                            .getProperty(".pxFlow");
                    Iterator flows = pxFlow.iterator();
                    while (flows.hasNext()) {
                        ClipboardProperty thisFlow = (ClipboardProperty) flows
                                .next();
                        if (thisFlow.getStringValue(".pyIssuedFromFlow")
                                .equals(flowName))
                            pega_procom_flow.RemoveFlow(useAsPrimPage,
                                    thisFlow.getName(), tools);
                    }
                }

                ProblemFlow(e, taskName);
                return false;
            } else
                throw e;
        } catch (PRRuntimeError e) {
            if (issuedAs.startsWith("SPLIT_")) {
                // Reset pyFlowWaitCount to 0
                currentFlow.getProperty("pyFlowWaitCount").setValue(0);

                // Remove any other flows that were split off (so we can start fresh)
                ClipboardProperty pxFlow = useAsPrimPage.getProperty(".pxFlow");
                Iterator flows = pxFlow.iterator();
                while (flows.hasNext()) {
                    ClipboardProperty thisFlow = (ClipboardProperty) flows
                            .next();
                    if (thisFlow.getStringValue(".pyIssuedFromFlow").equals(
                            flowName))
                        pega_procom_flow.RemoveFlow(useAsPrimPage,
                                thisFlow.getName(), tools);
                }
            }

            if (e instanceof RuleNotFoundException && bDraftMode) {
                bCalledNonExistentRule = true;
                return true;
            } else {
                ProblemFlow(e, taskName);
                return false;
            }
        }
        // The called flow may have called an activity which may have raised a ticket
        // which ended this flow
        if (CheckIfFlowHasEnded(false) == true)
            return false;

        //Check to see if the state of the flow has change out from under us 
        if (strCurrentFlowStep != null
                && !strCurrentFlowStep.equals(currentFlow
                        .getString("pyCurrentFlowStep")))
            return false; // Abort this chain as the state has changed

        // No exceptions
        return true;
    }

    private boolean EvaluateConnector(String connectorType,
            String connectorValue, ClipboardPage useAsPrimPage) {
        if (connectorType.equalsIgnoreCase("ALWAYS")
                || connectorType.equalsIgnoreCase("ELSE")) {
            return true;
        } else if (connectorType.equalsIgnoreCase("STATUS")) {
            return connectorValue.equalsIgnoreCase(currentFlow
                    .getString(".pxLastActivityStatus"));
        } else if (connectorType.equalsIgnoreCase("PERCENTAGE")) {
            int random = useAsPrimPage.getInteger("pyRandomNumber");
            String rangeStart = pega_rules_string.whatComesBeforeFirst(
                    connectorValue, '-');
            int rangeStartVal = Integer.parseInt(rangeStart);
            String rangeEnd = pega_rules_string.whatComesAfterFirst(
                    connectorValue, '-');
            int rangeEndVal = Integer.parseInt(rangeEnd);
            return (random >= rangeStartVal) && (random <= rangeEndVal);
        } else if (connectorType.equalsIgnoreCase("WHEN")) {
            // First create a new parameter page for this when
            ParameterPage newParamsPage = createNewParamsPage(currentFlow
                    .getString(".pyLastFlowStep"));

            if (connectorValue.equalsIgnoreCase("true"))
                return true;
            else if (connectorValue.equalsIgnoreCase("false"))
                return false;

            // call a Rule-Obj-When record
            boolean retVal = false;
            PRStackFrame frame = pega.pushStackFrame("ProcessWhenConnector",
                    newParamsPage, useAsPrimPage, false, false);
            try {
                StringMap params = new HashStringMap();
                params.putString("pxObjClass", "Rule-Obj-When");
                params.putString("pyClassName", useAsPrimPage.getClassName());
                params.putString("pyBlockName", connectorValue);
                retVal = tools.evaluateWhen(params);
            } catch (PRRuntimeError e) {
                String getNavigationPath = tools
                        .getParamValue("GetNavigationPath");
                if (getNavigationPath != null
                        && getNavigationPath.equals("true"))
                    return false;
                else {
                    if (e instanceof com.pega.pegarules.pub.runtime.IndeterminateConditionalException
                            && bDraftMode)
                        DraftModeFlow("DraftModeChoice",
                                currentFlow.getString(".pyLastFlowStep"));
                    else
                        ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    connectorFlowProblem = true;
                }
            } catch (RuntimeException e) {
                String getNavigationPath = tools
                        .getParamValue("GetNavigationPath");
                if (getNavigationPath != null
                        && getNavigationPath.equals("true"))
                    return false;
                else {
                    ProblemFlow(e, currentFlow.getString(".pyLastFlowStep"));
                    connectorFlowProblem = true;
                }
            }

            finally {
                pega.popStackFrame(frame, false);
                return retVal;
            }
        } else {
            // not one of our standard types, just return true
            return true;
        }
    }

    // This version of ProblemFlow is for conditions that flow java gen detects as incorrect
    private void ProblemFlow(String ErrorMessage, String TaskInError) {
        ProblemFlow(ErrorMessage, TaskInError, null);
    }

    // This version of ProblemFlow is for caught throwables
    private void ProblemFlow(Throwable t, String TaskInError) {
        String errorMessage;
        if (t instanceof PRRuntimeError)
            errorMessage = t.getMessage();
        if (t instanceof PRRuntimeException)
            errorMessage = t.getMessage();
        else if (t instanceof PRAppRuntimeException) {
            errorMessage = t.getMessage();
            if (!(((PRAppRuntimeException) t).getRuleSetName()
                    .equals("Pega-ProCom"))) {
                // Flow code only turns Pega-ProCom PRAppRuntimeExceptions into problem flows
                throw (PRAppRuntimeException) t;
            }
        } else
            errorMessage = t.toString();
        ProblemFlow(errorMessage, TaskInError, t);
    }

    // This version of ProblemFlow is the common code; called if the throwable's error message isn't sufficient
    private void ProblemFlow(String ErrorMessage, String TaskInError,
            Throwable t) {
        bInProblemFlow = true;
        String ProblemFlowName = flowName;
        // First log the problem flow to the tracer
        String localizedMessage = tools.getLocalizedTextForString(
                "pxRequestor.pyMessageLabel", "	[@baseclass.pyMessageLabel]"
                        + ErrorMessage);
        LogTraceEvent(mRuleSet, localizedMessage, flowName, "Flow Message",
                "Problem Flow", mFlowHandle, TaskInError);

        // If there are problems with flow problems,
        // we could have an infinite loop and we don't want that!
        // Also since flowproblems calls correspondence flows,
        // we could have an infinite loop in that one too.
        if (currentFlow != null) {
            String currentFlowType = currentFlow.getString("pyFlowType");
            if (currentFlowType.equalsIgnoreCase("FlowProblems")
                    || currentFlowType.equalsIgnoreCase("VerifySendCorr")
                    || currentFlowType.equalsIgnoreCase("FixCorrespondence")) {
                LogTraceEvent(mRuleSet,
                        "Problem Flow aborted because flow is of type "
                                + currentFlowType, flowName, "Flow Message",
                        "", mFlowHandle, TaskInError);

                // Now if the current flow is FlowProblems itself, we need to remove it and
                // report its message (it probably aborted because there is no routing operator).
                if (currentFlowType.equalsIgnoreCase("FlowProblems")) {
                    ProblemFlowName = pyFlowParametersPage
                            .getString("ProblemFlowName");
                    TaskInError = pyFlowParametersPage
                            .getString("StepWithProblem");
                    ErrorMessage = pyFlowParametersPage
                            .getString("ErrorMessage");
                    localizedMessage = tools.getLocalizedTextForString(
                            "pxRequestor.pyMessageLabel",
                            "	[@baseclass.pyMessageLabel]" + ErrorMessage);
                    pega_procom_flow.RemoveFlow(workPage, flowName, tools);
                }

                // We don't want to lose the error completely though
                interestPage.addMessage("ProblemFlow\t" + ProblemFlowName
                        + "\t" + TaskInError + "\t" + localizedMessage);
                oLog.error("Problem flow abort due to system flow "
                        + currentFlowType + " being the issue", t);
                return;
            } else if (callerParameterPage.getString("ProcessingMode") != null
                    && callerParameterPage.getString("ProcessingMode").equals(
                            "Bulk")) {
                LogTraceEvent(mRuleSet,
                        "Problem Flow aborted because of bulk processing",
                        flowName, "Flow Message", "", mFlowHandle, TaskInError);

                // We don't want to lose the error completely though
                interestPage.addMessage("ProblemFlow\t" + ProblemFlowName
                        + "\t" + TaskInError + "\t" + localizedMessage);
                oLog.error("Problem flow abort due to bulk processing", t);
                return;
            }

            ParameterPage newParamsPage = new ParameterPage();
            newParamsPage.putString("ProblemFlowType", currentFlowType);
            newParamsPage.putString("ProblemFlowStep", TaskInError);
            newParamsPage.putString("ProblemFlowName", flowName);
            newParamsPage.putString("ErrorMessage", ErrorMessage);
            if (t != null)
                newParamsPage.putString("ProblemFlowStackTrace",
                        pega_rules_utilities.getStackTrace(t));
            if (InRetryConnection == true)
                newParamsPage.putString("InRetryConnection", "true");
            else
                newParamsPage.putString("InRetryConnection", "false");

            StringMap params = new HashStringMap();
            params.putString("pxObjClass", "Rule-Obj-Activity");
            params.putString("pyClassName", workPage.getClassName());
            params.putString("pyActivityName", "ProblemFlow");
            tools.doActivity(params, workPage, newParamsPage);
        } else {
            // We don't want to lose the error completely though
            interestPage.addMessage("ProblemFlow\t" + ProblemFlowName + "\t"
                    + TaskInError + "\t" + localizedMessage);
            oLog.error("Problem flow abort due to currentFlow null", t);
        }
    }

    private void ConnectionProblem(String ExceptionType, String HandlerFlow,
            String ErrorMessage, String TaskInError) {
        // First log the problem flow to the tracer
        LogTraceEvent(mRuleSet, ErrorMessage, flowName, "Flow Message",
                "Connection Problem", mFlowHandle, TaskInError);

        ParameterPage newParamsPage = new ParameterPage();
        newParamsPage.putString("ProblemFlowType",
                currentFlow.getString("pyFlowType"));
        newParamsPage.putString("ProblemFlowStep", TaskInError);
        newParamsPage.putString("ProblemFlowName", flowName);
        newParamsPage.putString("ExceptionType", ExceptionType);
        newParamsPage.putString("HandlerFlow", HandlerFlow);
        newParamsPage.putString("ErrorMessage", ErrorMessage);

        StringMap params = new HashStringMap();
        params.putString("pxObjClass", "Rule-Obj-Activity");
        params.putString("pyClassName", workPage.getClassName());
        params.putString("pyActivityName", "ConnectionProblem");
        tools.doActivity(params, workPage, newParamsPage);
    }

    // The draft mode functionality is very similar to problem flows
    private void DraftModeFlow(String ErrorMessage, String TaskInError) {
        DraftModeFlow(ErrorMessage, TaskInError, "", "");
    }

    private void DraftModeFlow(String ErrorMessage, String TaskInError,
            String toShapeID, String toShapeName) {
        ParameterPage newParamsPage = new ParameterPage();
        String currentFlowType = currentFlow.getString("pyFlowType");
        newParamsPage.putString("ProblemFlowType", currentFlowType);
        newParamsPage.putString("ProblemFlowStep", TaskInError);
        newParamsPage.putString("ProblemFlowName", flowName);
        newParamsPage.putString("ErrorMessage", ErrorMessage);
        newParamsPage.putString("toShapeID", toShapeID);
        newParamsPage.putString("toShapeName", toShapeName);
        String draftFlowName = pega_procom_flow.AddFlow(tools, workPage,
                "DraftModeChoice", "DraftModeChoice", "");
        pega_procom_flow.StartFlow(draftFlowName, workPage, newParamsPage);
    }

    private void StuckFlow() {
        String strStatus = currentFlow.getString(".pxLastActivityStatus")
                .trim();
        String strStep = currentFlow.getString(".pyLastFlowStep");
        String strStepLabel = currentFlow.getString(".pyLastFlowStepLabel");
        if (strStepLabel.length() == 0)
            strStepLabel = strStep;
        if (strStatus.length() > 0)
            ProblemFlow("ProblemFlow_StuckFlowConnectorNotFound\t"
                    + strStepLabel + "\t" + strStatus, strStep);
        else
            ProblemFlow("ProblemFlow_StuckFlowConnectorMissing\t"
                    + strStepLabel, strStep);
    }

    private boolean RestartIssuingFlow(String pyIssuedFromObject,
            String pyIssuedFromFlow, String pyIssuedAs,
            String pyIssuedFromTask, String pyIssuedFromPage) {
        //if this Flow was initiated from within another flow, properties pyIssuedFromObject and pyIssuedFromFlow
        //will be populated with a reference to that Object/Flow. What we need to do here is restart that
        //flow, since it is waiting for this one to finish.
        ClipboardPage useAsPrimPage = workPage;

        if (!isTemporaryObject && !pyIssuedFromObject.equals("")
                && !pyIssuedFromObject.equals(workPageHandle)) {
            // This other flow is NOT defined on the same object, so the work page is different
            // First we'll see if the other object is still on the clipboard.
            // Otherwise, we'll put this new object on the clipboard with the name "pyPrevObj"
            ParameterPage params = new ParameterPage();
            ClipboardPage cp = tools.findPageByHandle(pyIssuedFromObject);
            if (cp != null) {
                // We have found the issuing object on the clipboard.
                // We must make sure we have a lock on it though.
                useAsPrimPage = cp;
                params.putString("workPage", useAsPrimPage.getName());
                params.putString("workHandle", pyIssuedFromObject);
                params.putString("workOnly", "true");
                pega_rules_utilities.callActivity(useAsPrimPage, "WorkLock",
                        params);
                if (params.getAsInteger("SuccessIndicator") < 0) {
                    LogTraceEvent(
                            mRuleSet,
                            "Could not lock previous object "
                                    + pyIssuedFromObject + " due to "
                                    + params.getString("FailureInfo"), "",
                            "Flow Message", "", mFlowHandle, "");
                    workPage.addMessage(params.getString("FailureInfo"));
                    return false;
                }
            }

            else {
                // First determine what the page name will be; being careful not to overwrite anything already there
                if (!pyIssuedFromPage.equals("")
                        && tools.findPage(pyIssuedFromPage) == null)
                    params.putString("workPage", pyIssuedFromPage);
                else
                    params.putString("workPage",
                            pega_rules_utilities.getUniquePageName("pyPrevObj"));
                // Create a temporary work page for calling WorkLock
                cp = tools.createPage("Work-", params.getString("workPage"));
                params.putString("workHandle", pyIssuedFromObject);
                params.putString("workOnly", "true");
                pega_rules_utilities.callActivity(cp, "WorkLock", params);
                if (params.getAsInteger("SuccessIndicator") < 0) {
                    LogTraceEvent(
                            mRuleSet,
                            "Could not open previous object "
                                    + pyIssuedFromObject + " due to "
                                    + params.getString("FailureInfo"), "",
                            "Flow Message", "", mFlowHandle, "");
                    workPage.addMessage(params.getString("FailureInfo"));
                    return false;
                } else {
                    useAsPrimPage = tools
                            .findPage(params.getString("workPage"));
                }
            }
        }

        boolean restartFlow = false;
        String strFlowPropertyName = ".pxFlow(" + pyIssuedFromFlow + ")";
        ClipboardProperty pxFlowProperty = useAsPrimPage
                .getIfPresent(strFlowPropertyName);
        if (pxFlowProperty == null) {
            LogTraceEvent(mRuleSet, "Issuing flow " + pyIssuedFromFlow
                    + " not found", "", "Flow Message", "", mFlowHandle, "");
            // We just exit quietly in that case, a ticket probably already moved things along
            return false;
        }
        ClipboardPage issuingFlowPage = pxFlowProperty.getPageValue();

        if (pyIssuedAs.equalsIgnoreCase("SPLIT_ALL")) {
            // The issuing flow is waiting for all of the split flows to finish.
            ClipboardProperty pyFlowWaitCount = issuingFlowPage
                    .getProperty(".pyFlowWaitCount");
            int fwCount = pyFlowWaitCount.toInteger();
            fwCount--;
            pyFlowWaitCount.setValue(fwCount);
            if (fwCount <= 0)
                restartFlow = true;
            else {
                // We need to save the issuing work object since we changed a property but the flow isn't restarting
                ParameterPage pp = new ParameterPage();
                pega_rules_utilities.callActivity(useAsPrimPage,
                        "RecalculateAndSave", pp);
            }
        }

        else if (pyIssuedAs.equalsIgnoreCase("SPLIT_ANY")) {
            // The issuing flow is waiting for any of the split flows to finish. This one
            // has finished first; delete all the others and reissue the flow
            ClipboardProperty pxFlow = useAsPrimPage.getProperty(".pxFlow");
            Iterator flows = pxFlow.iterator();
            while (flows.hasNext()) {
                ClipboardProperty thisFlow = (ClipboardProperty) flows.next();
                String thisFlowName = thisFlow.getName();
                // it is possible that this flow page has already been removed
                if (useAsPrimPage.getIfPresent(".pxFlow(" + thisFlowName + ")") == null)
                    continue;
                String thisFlowIssuedFrom = thisFlow
                        .getStringValue(".pyIssuedFromFlow");
                String thisFlowIssuedAs = thisFlow
                        .getStringValue(".pyIssuedAs");
                // If the flow is not this one (the one ending), and it is from a SPLIT_ANY task,
                // and it was split by the same flow that issued this one, then delete it
                if (!thisFlowName.equals(flowName)
                        && thisFlowIssuedAs.equals("SPLIT_ANY")
                        && thisFlowIssuedFrom.equals(pyIssuedFromFlow)) {
                    pega_procom_flow.CleanUpSplit(useAsPrimPage,
                            thisFlow.getPageValue(), tools);
                    pega_procom_flow.RemoveFlow(useAsPrimPage, thisFlowName,
                            tools);
                }
            }
            restartFlow = true;
        }

        else if (pyIssuedAs.equalsIgnoreCase("SPLIT_SOME")) {
            restartFlow = true;
        }

        else {
            // regular FLOW
            String lastFlowStep = issuingFlowPage.getString(".pyLastFlowStep");
            if (pyIssuedFromTask.length() > 0
                    && !lastFlowStep.equals(pyIssuedFromTask))
                // the flow has moved on, probably via ticket, from where it was
                restartFlow = false;
            else
                restartFlow = true;
        }

        if (restartFlow == true) {
            // launch the issuing flow
            ClipboardPage intPage = pega_procom_flow.GetInterestPage(tools,
                    useAsPrimPage, issuingFlowPage);
            ParameterPage issuingParams = new ParameterPage();
            issuingParams.putString("flowName", pyIssuedFromFlow);
            issuingParams.putString("ReferencePageName",
                    useAsPrimPage.getName());

            StringMap params = new HashStringMap();
            params.putString("pxObjClass", "Rule-Obj-Flow");
            params.putString("pyClassName", intPage.getClassName());
            params.putString("pyFlowType",
                    issuingFlowPage.getString("pyFlowType"));
            tools.doActivity(params, intPage, issuingParams);
        }

        return restartFlow;
    }

    private void AddTaskCost(String strCost, String taskName) {
        // Create the page to hold the flow cost information
        ClipboardPage pgFlowTaskCost = tools.createPage("Embed-WorkFlowCost",
                "");
        pgFlowTaskCost.putString("pyCost", strCost);
        pgFlowTaskCost.putString("pyName", taskName);
        pgFlowTaskCost.putString("pyFlowName", flowName);

        // add the page to the work item's page list of flow actions
        ClipboardProperty pyFlowTaskCosts = workPage
                .getProperty(".pyFlowTaskCosts");
        pyFlowTaskCosts.add(pgFlowTaskCost);
    }

    private void AddTaskToFlowPath(String taskName, String taskType,
            String taskLabel) {
        if (taskType.equals("ASSIGNMENT"))
            AddTaskToFlowPath(taskName, taskType, taskLabel, true, "", false);
        else
            AddTaskToFlowPath(taskName, taskType, taskLabel, false, "", false);
    }

    private void AddTaskToFlowPath(String taskName, String taskType,
            String taskLabel, boolean display, String disabledWhen,
            boolean ProcessFAOnJump) {
        // We must first make sure this task isn't already in the path history
        ClipboardProperty taskPath = currentFlow.getProperty(".pyFlowPath");
        boolean foundTaskInHistory = false;
        java.util.Iterator taskIt = taskPath.iterator();
        while (taskIt.hasNext() && foundTaskInHistory == false) {
            ClipboardProperty taskPageRef = (ClipboardProperty) taskIt.next();
            ClipboardPage taskPage = taskPageRef.getPageValue();
            String strHistoryTaskName = taskPage.getString(".pyFlowStep");
            if (strHistoryTaskName.equals(taskName)) {
                foundTaskInHistory = true;
                // If the task label has changed (split-for-each can do that), take the new one
                if (!taskLabel.equals("")
                        && !taskLabel
                                .equals(taskPage.getString(".pyStepLabel")))
                    taskPage.putString(".pyStepLabel", taskLabel);
                if (taskType.equals("SPLITFOREACH")
                        && currentSFESubscript.length() > 0) {
                    taskPage.getProperty(".pyCurrentSplitSubscript").setValue(
                            currentSFESubscript);
                    currentSFESubscript = "";
                }
                if (!buildFlowPage) {
                    // If this task page contains completed subflow information, we must
                    // remove it
                    ClipboardProperty pyCompletedSubFlowPath = taskPage
                            .getIfPresent(".pyCompletedSubFlowPath");
                    if (pyCompletedSubFlowPath != null) {
                        pyCompletedSubFlowPath.removeFromClipboard();
                    }
                }
            }
        }

        if (foundTaskInHistory) {
            // remove all tasks beyond this one
            int size = taskPath.size();
            for (int i = size; i > 0; i--) {
                ClipboardPage taskPage = taskPath.getPageValue(i);
                String strHistoryTaskName = taskPage.getString(".pyFlowStep");
                if (strHistoryTaskName.equals(taskName))
                    break;
                else
                    taskPath.remove(i);
            }
        } else {
            // check to see if we can find the task in the flowdata
            ClipboardPage pgFlowData = tools.findPage("pyFlowData");
            if (pgFlowData != null) {
                String currentFlowType = currentFlow.getString(".pyFlowType");
                String parentFlowPath = currentFlow
                        .getString(".pyParentFlowPath");
                ClipboardPage flowTaskInFlowData = pega_processengine_flowutilities
                        .findEntryPointInFlowData(pgFlowData, taskName,
                                parentFlowPath);
                if (flowTaskInFlowData != null) {
                    // Remove everything that comes after this step in the flowdata from the flowpath
                    int taskIndexInFlow = flowTaskInFlowData
                            .getParentProperty().indexOf();
                    ClipboardProperty pyFlowDataSteps = flowTaskInFlowData
                            .getParentProperty().getParentProperty();
                    for (int i = taskIndexInFlow++; i <= pyFlowDataSteps.size(); i++) {
                        ClipboardPage pgNextStep = pyFlowDataSteps
                                .getPageValue(i);
                        String strNextStepName = pgNextStep
                                .getString(".pyFlowStep");
                        // Now remove that future step from the flowpath, if it exists
                        int size = taskPath.size();
                        for (int j = size; j > 0; j--) {
                            ClipboardPage taskPage = taskPath.getPageValue(j);
                            String strHistoryTaskName = taskPage
                                    .getString(".pyFlowStep");
                            if (strHistoryTaskName.equals(strNextStepName)) {
                                taskPath.remove(j);
                                break;
                            }
                        }
                    }
                }
            }

            // if the task isn't already in the path history, add it
            ClipboardPage newTaskPage = tools.createPage(
                    "Embed-PegaEPRO-FlowPath", "");
            newTaskPage.putString(".pyFlowStep", taskName);
            newTaskPage.putString(".pyStepType", taskType);
            if (taskLabel.length() > 0)
                newTaskPage.putString(".pyStepLabel", taskLabel);
            else
                newTaskPage.putString(".pyStepLabel", taskName);
            newTaskPage.getProperty(".pyDisplay").setValue(display);
            newTaskPage.getProperty(".pyDisabledWhenRule").setValue(
                    disabledWhen);
            newTaskPage.getProperty(".pyFAProcessOnJump").setValue(
                    ProcessFAOnJump);
            if (taskType.equals("SPLITFOREACH")
                    && currentSFESubscript.length() > 0) {
                newTaskPage.getProperty(".pyCurrentSplitSubscript").setValue(
                        currentSFESubscript);
                currentSFESubscript = "";
            }
            taskPath.add(newTaskPage);
        }
        // Add step to stage history
        pega_processengine_flowutilities.pzAddStepToStageHistory(tools,
                currentFlow, taskName, taskType, taskLabel);
    }

    private boolean AddFlowAction(String actionName, String strActionType,
            boolean isSelectedAction, boolean displaySeparatorAbove) {
        ParameterPage newParamsPage = new ParameterPage();
        newParamsPage.putString("pyFlowActionPurpose", "ADD");
        newParamsPage.putString("ActionType", strActionType);
        newParamsPage.putString("assignPage",
                tools.getParamValue("AssignmentPageName"));
        newParamsPage.putString("isSelectedAction", new Boolean(
                isSelectedAction).toString());
        newParamsPage.putString("displaySeparatorAbove", new Boolean(
                displaySeparatorAbove).toString());
        newParamsPage.putString("PopulateForBulk",
                tools.getParamValue("PopulateForBulk"));

        StringMap flowAction = new HashStringMap();
        flowAction.putString("pxObjClass", "Rule-Obj-FlowAction");
        if (strActionType.equals("LocalCase")) {
            flowAction.putString("pyClassName", workPage.getClassName());
        } else {
            flowAction.putString("pyClassName", interestPage.getClassName());
        }
        flowAction.putString("pyActionName", actionName);

        if (bDraftMode && actionName.startsWith("[DRAFTMODE]")) {
            // The flow action name was left blank.
            // Use ActionStub as the model, then change a few props to fake the unspecified flow action
            flowAction.putString("pyActionName", "ActionStubDraftMode");
            tools.doAction(flowAction, interestPage, newParamsPage);
            ClipboardPage newAssignPage = tools.findPage(newParamsPage
                    .getString("assignPage"));
            String strActionLabel = pega_rules_string.whatComesAfterLast(
                    actionName, '|');
            actionName = pega_rules_string.whatComesBeforeLast(actionName, '|');
            newAssignPage.getProperty(".pyAssignActions(<LAST>).pyActionLabel")
                    .setValue(strActionLabel);
            newAssignPage.getProperty(
                    ".pyAssignActions(<LAST>).pyActionInstructions").setValue(
                    actionName);
            return true;
        }

        try {
            tools.doAction(flowAction, interestPage, newParamsPage);
        } catch (PRRuntimeError rnfe) {
            // if the flow action was not found, we just won't add it unless we are in draft mode
            if (rnfe instanceof RuleNotFoundException && bDraftMode) {
                // Use ActionStub as the model, then change a few props to fake the missing flow action
                flowAction.putString("pyActionName", "ActionStubDraftMode");
                tools.doAction(flowAction, interestPage, newParamsPage);
                ClipboardPage newAssignPage = tools.findPage(newParamsPage
                        .getString("assignPage"));
                newAssignPage.getProperty(
                        ".pyAssignActions(<LAST>).pyActionLabel").setValue(
                        currentFlow.getString(".pyLastFlowStepLabel"));
                newAssignPage.getProperty(
                        ".pyAssignActions(<LAST>).pyActionInstructions")
                        .setValue(actionName);
                return true;
            } else
                return false;
        } catch (PRRuntimeException pre) {
            // shouldn't get here.
            oLog.error(pre.getMessage(), pre);
            return false;
        }

        return newParamsPage.getAsBoolean("AddOpRetVal");
    }

    private boolean[] PopulateCaseFlowActions(boolean isFirstAction,
            boolean isFirstLocalAction, boolean localsAtTop) {
        // Populate the stage-wide actions for this assignment
        boolean[] retParams = new boolean[2];
        //call the case type to get the list of stage-wide local actions, which is put into pyActionsList
        ParameterPage params = new ParameterPage();
        pega_rules_utilities.pxCallCaseType(workPage, "STAGE_GETACTIONS",
                params);

        //read the results and add them to the list
        ClipboardPage actionsPage = tools.findPage("pyActionsList");
        ClipboardPage actionsPageCase = tools.findPage("pyCaseActionsList");
        retParams = addPageToFAList(actionsPage, isFirstAction,
                isFirstLocalAction);
        retParams = addPageToFAList(actionsPageCase, retParams[0], retParams[1]);
        return retParams;
    }

    private boolean[] addPageToFAList(ClipboardPage actionsPage,
            boolean isFirstAction, boolean isFirstLocalAction) {
        if (actionsPage != null) {
            ClipboardProperty pxResults = actionsPage.getProperty("pxResults");
            if (pxResults != null) {
                Iterator it = pxResults.iterator();
                while (it.hasNext()) {
                    ClipboardProperty result = (ClipboardProperty) it.next();
                    String actionName = result.getPageValue().getString(
                            "pyActionName");
                    if (!tools.getParamValue("GetSpecificAction").equals("")) {
                        if (tools.getParamValue("GetSpecificAction")
                                .equalsIgnoreCase(actionName))
                            if (AddFlowAction(actionName, "LocalCase",
                                    isFirstAction, isFirstLocalAction)) {
                                isFirstAction = false;
                                isFirstLocalAction = false;
                            }
                    } else if (AddFlowAction(actionName, "LocalCase",
                            isFirstAction, isFirstLocalAction)) {
                        isFirstAction = false;
                        isFirstLocalAction = false;
                    }
                }
            }
            actionsPage.removeFromClipboard();
        }
        boolean[] retParams = new boolean[2];
        retParams[0] = isFirstAction;
        retParams[1] = isFirstLocalAction;
        return retParams;
    }

    //Launches companion flow to use for flow-scoped SLA and returns the name of the created flow
    private String launchCompanionFlow(ClipboardPage workPage,
            String interestPageName, String pyFlowSLA) {
        String companionFlowName = pega_procom_flow.AddFlow(tools, workPage,
                "pzInternalProcessFlow", "pzInternalProcessFlow",
                interestPageName);
        ParameterPage pp = new ParameterPage();
        pp.putParamValue("SLAName", pyFlowSLA);
        pega_procom_flow.StartFlow(companionFlowName, workPage, pp);

        return companionFlowName;
    }

    private static final com.pega.pegarules.priv.tracer.RuleTraceInfo oTraceInfo_0 = new com.pega.pegarules.priv.tracer.RuleTraceInfo(
            "RULE-OBJ-FLOW OOO-PGB-WORK-DEMO CREATEFORM_DEFAULT #20211127T110301.884 GMT",
            "OOO-PGB-Work-Demo CreateForm_Default", "PGB", "01-01-01",
            "20211128T112343.957 GMT");
}
