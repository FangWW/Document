package com.javapatterns.state.audioplayer;

public class PlayState extends AudioPlayerState
{
    public PlayState(AudioPlayer player)
    {
        super(player);
    }

    public PlayState() {
    }

    public void stopButton(AudioPlayer player)
    {
        player.setState(this.stopState);
        player.stopPlay();
    }
}
