package shiqi;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by
 *
 * @author=蓝十七
 * @on 2018-05-13-16:17
 */

public class HelloActivti {

    /*public static ProcessEngine processEngine;

    public static ProcessEngine getProcessEngine(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        return processEngine;
    }

    static {
        getProcessEngine();
    }*/



    @Test
    public void createActivitiEngin(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        engineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        engineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activitiDB1?createDatabaseIfNotExist=true&amp;useUnicode=true&amp;characterEncoding=utf8");
        engineConfiguration.setJdbcUsername("root");
        engineConfiguration.setJdbcPassword("xq1996621");
        engineConfiguration.setDatabaseSchemaUpdate("true");
        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
    }


    @Test
    public void createActivitiEngin1(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
    }

    @Test
    public void deploy(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        RepositoryService respositoryService=processEngine.getRepositoryService();
        Deployment deploy =respositoryService.createDeployment().addClasspathResource("BillActiviti.bpmn").name("请求单流程").category("办公类别").deploy();
        System.out.println("部署id"+deploy.getId());
        System.out.println("部署名称"+deploy.getName());

    }

    @Test
    public void startProcess(){
        String processDefiKey="myProcess_1";
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        RuntimeService runtimeService=processEngine.getRuntimeService();
        ProcessInstance pi=runtimeService.startProcessInstanceByKey(processDefiKey);
        System.out.println("流程实例id："+pi.getId());
        System.out.println("流程定义id："+pi.getProcessDefinitionId());
    }


    @Test
    public void queryTask(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String assignee="十七";
        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery=taskService.createTaskQuery();
        List<Task> list=taskQuery.taskAssignee(assignee).list();
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println(task.getAssignee());
                System.out.println(task.getId());
                System.out.println(task.getName());
            }
        }
    }

    @Test
    public void compileTask(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String taskId="2504";
        TaskService taskService=processEngine.getTaskService();
        taskService.complete(taskId);
        System.out.println("compile");
    }

    @Test
    public void queryTask1(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String assignee="班主任";
        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery=taskService.createTaskQuery();
        List<Task> list=taskQuery.taskAssignee(assignee).list();
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println(task.getAssignee());
                System.out.println(task.getId());
                System.out.println(task.getName());
            }
        }
    }

    @Test
    public void compileTask1(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String taskId="5002";
        TaskService taskService=processEngine.getTaskService();
        taskService.complete(taskId);
        System.out.println("compile");
    }

    @Test
    public void queryTask2(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String assignee="任课老师";
        TaskService taskService=processEngine.getTaskService();
        TaskQuery taskQuery=taskService.createTaskQuery();
        List<Task> list=taskQuery.taskAssignee(assignee).list();
        if(list!=null&&list.size()>0){
            for(Task task:list){
                System.out.println(task.getAssignee());
                System.out.println(task.getId());
                System.out.println(task.getName());
            }
        }
    }

    @Test
    public void compileTask2(){
        ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        System.out.println("使用配置文件");
        String taskId="7502";
        TaskService taskService=processEngine.getTaskService();
        taskService.complete(taskId);
        System.out.println("compile");
    }


    @Test
    public void viewImage(){
        ProcessEngineConfiguration engineConfiguration= ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=engineConfiguration.buildProcessEngine();
        String deploymentId="1";
        String imageName=null;
        List<String> resourceNames=processEngine.getRepositoryService().getDeploymentResourceNames(deploymentId);
        if(resourceNames!=null&&resourceNames.size()>0){
            for(String temp:resourceNames){
                if(temp.indexOf(".png")>0){
                    imageName=temp;
                }
            }
        }

        InputStream resourceAsStream=processEngine.getRepositoryService().getResourceAsStream(deploymentId,imageName);
        File file=new File("d:/"+imageName);
        try {
            FileUtils.copyInputStreamToFile(resourceAsStream,file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
