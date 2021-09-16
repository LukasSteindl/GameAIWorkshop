The first part of this workshop is based on Prof. Eidenbergers course Strategy Gaming at TU Vienna in 2019.
The lab uses Robert Fischers Mancala Board Game Engine. (https://github.com/metzzo/mancala)
The second part consists of a brief introduction of reinforcement learning. The material is mainly taken from public internet and based on an online course of David Silver et. al. 
The presentation includes several youtube links that describe the evolution from classic AI methods up to most recent innovations.



The simpledemos folder contains Jupyter Notebooks that can be executed on any windows machine. 
(see steps below to configure your virtual conda python environment!)
The gym environment can result in blue screens on Windows. Suspecting low display memory due to second monitor as the problem.

step 1) 
install tensorflow including GPU 
https://www.tensorflow.org/install/gpu


conda actiate rl
pip install --upgrade pip
pip install tensorflow
pip install gym
pip install gym[atari]


pip install 'imageio==2.4.0'
pip install pyvirtualdisplay
pip install tf-agents
pip install matplotlib
conda install ffmpeg -c conda-forge


import gym

there is a bug in the monitor wrapper in the current version 0.18.0 of gym that is distributed via pypi https://github.com/openai/gym/issues/1925
it is fixed in this version -> pip install gym==0.18.3
with that one can render videos to see what the agent is doing.



The Reinforcment Learning Tutorials can be found here: 
https://github.com/Azure/MachineLearningNotebooks/tree/master/how-to-use-azureml/reinforcement-learning

Note that the docker images result in problems when rendering the agents gameplay as video.

the fixed docker image can be found in my fork of the repo:
https://github.com/LukasSteindl/MachineLearningNotebooks/blob/master/how-to-use-azureml/reinforcement-learning/cartpole-on-single-compute/files/docker/Dockerfile

(there is an outstanding pull request)