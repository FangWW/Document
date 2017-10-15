package com.javapatterns.state.audioplayer;

public class Julie
{
    public void main(String[] args)
    {
        player = new AudioPlayer();

        player.startButton();
        player.stopButton();
    }

    /**
     * @link aggregation
     * @directed 
     */
    private static AudioPlayer player;
}
