package com.javapatterns.state.audioplayer;

abstract public class AudioPlayerState
{
    protected static AudioPlayerState initialState;
    protected static final AudioPlayerState playState = new PlayState();
    protected static final AudioPlayerState stopState = new StopState();

    protected AudioPlayerState(AudioPlayer player)
    {
    }

    public AudioPlayerState() {
    }

    public void playButton(){ }

    public void stopButton(){ }
}
