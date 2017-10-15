package com.javapatterns.chainofresp.flowerpassing;

public class DrumBeater 
{
    private static Player player;

	static public void main(String[] args)
	{
        JiaMu jiaMu = new JiaMu(null);

		jiaMu.setSuccessor( new JiaShe (
            new JiaZheng(
            new JiaBaoYu(
            new JiaHuan( jiaMu ) ) ) ) );

        player = jiaMu;

		player.handle(4);
	}
}
