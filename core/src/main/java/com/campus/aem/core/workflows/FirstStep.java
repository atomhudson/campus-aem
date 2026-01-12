package com.campus.aem.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.model.WorkflowNode;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component(service = WorkflowProcess.class,
        property = {"process.label=Workflow Process for First Step" })
public class FirstStep implements WorkflowProcess {
    private static final Logger log = LoggerFactory.getLogger(FirstStep.class);
    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        String workItemPath = workItem.getWorkflowData().getPayload().toString();
        WorkflowNode workItemNode = workItem.getNode();
        if (workItemNode!=null && !workItemPath.isEmpty()){
            String title = workItemNode.getTitle();
            log.info("--------->workItemTitle: {}",title);
            log.info("--------->workItemPath: {}",workItemPath);
            Session session = workflowSession.adaptTo(Session.class);
            try {
                Node node = session.getNode(workItemPath);
                if (node != null){
                    Node jcrContentNode = node.getNode("jcr:content");
                    jcrContentNode.addNode("first","nt:unstructured");
                    session.save();
                }
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
