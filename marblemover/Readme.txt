To test our Agent:

Start the Mancala Engine and Import our Agent Jar file.
The class path should be set to: at.pwd.marblemover.MarbleMoverAgent
The Screenshot HowToAddTheAgentJarToMancalaEngine.png also shows how to do this.
test
---------------------------------------------------

To debug our Project: 
The Source Folder contains our development project. 
One can debug the project by opening this folder as working directory of visual studio code and install all
suggested java extensions. 

As the path to the prebuilt agents will change when you copy the folder to your local drive
you need to adjust it in the config.xml to point to the folders that contain the other Test Agents:
     
<agent>
<path>C:\Users\youruserhere\Source\lib\agents\randomagent-1.0-SNAPSHOT.jar</path>
<classname>at.pwd.randomagent.MancalaRandomAgent</classname>
</agent>
<agent>
<path>C:\Users\youruserhere\Source\lib\agents\MarlbeMoverAgent.jar</path>
<classname>at.pwd.marblemover.MarbleMoverAgent</classname>
</agent>

you may also just remove this section from the config.xml and later reimport the other agents. 



