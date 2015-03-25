///////////////////////////////////////////////////////////////////////////////
//
// This file is a part of the RecursiveTask" sample, which demonstrate users
// can create child sessions for running/yielded/resuming tasks. A parent
// task can backfill run a descendant task if it yields. 
// This file contains code which demonstrates the use of the Symphony API
// within the client for this sample. 
//
//
// Copyright International Business Machines Corp, 1992-2014. US Government
// Users Restricted Rights - Use, duplication or disclosure restricted by GSA
// ADP Schedule Contract with IBM Corp. 
// Accelerating Intelligence(TM). All rights reserved. 
//
// This exposed source code is the confidential and proprietary property of
// IBM Corporation. Your right to use is strictly limited by the terms of the
// license agreement entered into with IBM Corporation. 
//
/////////////////////////////////////////////////////////////////////////////// 
 
package com.platform.symphony.samples.RecursiveTask.client;

import com.platform.symphony.soam.*;
import com.platform.symphony.samples.RecursiveTask.common.*;

public class RecursiveTaskClient
{

  static String USAGE = "";
    public static void main(String args[]) throws Exception
    {
        try
        {
      int priority = 0;
      int ret = 0;
      int[] taskPerLevel = null; //no recursive
      String taskPerLevelS = "";
      int sleepInSecond = 0;
      for (int i = 0; i < args.length; i++) 
      {
        String name = args[i];
        if ( ++i >= args.length) 
        {
        System.out.println("invalid parameter");
        ret = 1;
        break;
        }
        if (name.equals("-p")) 
        {
        priority = Integer.parseInt(args[i]);
        } else if (name.equals("-r")){
          taskPerLevelS = args[i];
          String[] taskPerLevelList = args[i].split(",");
          taskPerLevel = new int[taskPerLevelList.length];
          for(int j = 0; i < taskPerLevelList.length; i++) {
            taskPerLevel[i] = Integer.parseInt(taskPerLevelList[i]);
          }
        
        } else if (name.equals("-t")) {
          sleepInSecond = Integer.parseInt(args[i]);     
        } else {
         System.out.println("Unkown parameter: " + name + "=" +args[i]);  
        }
      }
      
      if (ret != 0 || taskPerLevel == null) {
        System.out.println(USAGE);
        return;
      } else {
        System.out.println("priority=" + priority + "\niTaskPerLevel=" + taskPerLevelS
            + "\nsleepInSecond="+sleepInSecond);
      }
      
            /****************************************************************
             * We should initialize the API before using any API calls. 
             ****************************************************************/
            SoamFactory.initialize();
            
            // Set up application specific information to be supplied to
            // Symphony. 
            String appName = "RecursiveTaskJava";

            // Set up application authentication information using the
            // default security provider. Ensure it exists for the lifetime
            // of the connection. 
            DefaultSecurityCallback securityCB = new DefaultSecurityCallback("Guest", "Guest");

            Connection connection = null;
            try
            {
                // Connect to the specified application. 
                connection = SoamFactory.connect(appName, securityCB);

                // Retrieve and print our connection ID. 
                System.out.println("Connection ID: " + connection.getId());
                
                // Set up session creation attributes. 
                SessionCreationAttributes attributesForParent = new SessionCreationAttributes();
                attributesForParent.setSessionName("parentSession");
                attributesForParent.setSessionType("ShortRunningTasks");
                attributesForParent.setSessionFlags(Session.SYNC);
        if (priority > 0) {
          attributesForParent.setSessionPriority(priority);

        }
                
                // Create a session with the provided attributes. 
                Session session = null;
                try
                {
                    session = connection.createSession(attributesForParent);

                    // Retrieve and print session ID. 
                    System.out.println("Parent Session ID:" + session.getId());

                    // Now we will send some messages to our service. 
                    int tasksToSend = 1;
                    for (int taskCount = 0; taskCount < tasksToSend; taskCount++)
                    {
                        // Create a message. 
                        MyInput myInput = new MyInput(taskCount, "Hello Grid !!", true,
                            taskPerLevel, 0 /*level, 0=root*/, priority, sleepInSecond);

                        // Create task attributes. 
                        TaskSubmissionAttributes taskAttr = new TaskSubmissionAttributes();
                        taskAttr.setTaskInput(myInput);

                        // Send it. 
                        TaskInputHandle input = session.sendTaskInput(taskAttr);

                        // Retrieve and print task ID. 
                        System.out.println("Parent task submitted with ID : " + input.getId());
                    }
 
                    // Now get our results - will block here until all tasks
                    // retrieved. 
                    System.out.println("Fetching parent task results...");
                    EnumItems enumOutput = session.fetchTaskOutput(tasksToSend);
                
                    // Inspect results. 
                    TaskOutputHandle output = enumOutput.getNext();
                    while(output != null)
                    {
                        // Check for success of task. 
                        if (output.isSuccessful())
                        {
                            // Get the message returned from the service. 
                            MyOutput myOutput = (MyOutput)output.getTaskOutput();

                            // Display content of reply. 
                            System.out.println(myOutput.getString());
                        }
                        else
                        {
                            // Get the exception associated with this task. 
                            SoamException ex = output.getException();
                            System.out.println("Task Not Successful : ");
                            System.out.println(ex.toString());
                        }
                        output = enumOutput.getNext();
                    }
                    System.out.println("Parent Session <" + session.getId() + "> Done !!");
                } 
                finally
                {
                    /********************************************************
                     * Mandatory: Close session. 
                     ********************************************************/
                    if (session != null)
                    {
                        session.close();
                    }
                }
            }
            finally
            {
                /************************************************************
                 * Mandatory: Close connection. 
                 ************************************************************/
                if (connection != null)
                {
                    connection.close();
                }
            }
        }
        catch (Exception ex)
        {
            // Report exception. 
            System.out.println("exception caught ... " + ex.toString());
        }
        finally
        {
            /****************************************************************
             * It is important that we always uninitialize the API. This is
             * the only way to ensure proper shutdown of the interaction
             * between the client and the system. 
             ****************************************************************/
            SoamFactory.uninitialize();
            System.out.println("\nAll Done !!");
        }
    }
}
