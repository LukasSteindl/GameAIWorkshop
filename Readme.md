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