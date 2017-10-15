package com.javapatterns.command.lightandfan;

public class Switch {
        private Command UpCommand, DownCommand;
        public Switch( Command Up, Command Down) {
                UpCommand = Up; // concrete Command registers itself with the invoker 
                DownCommand = Down;
        }
        void flipUp( ) { // invoker calls back concrete Command, which executes the Command on the receiver 
                        UpCommand . execute ( ) ;


        }
        void flipDown( ) {
                        DownCommand . execute ( );
        }
}

