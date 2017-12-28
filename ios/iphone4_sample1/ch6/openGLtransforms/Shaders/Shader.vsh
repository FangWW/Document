//
//  Shader.vsh
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

attribute vec4 position;
attribute vec4 color;

varying vec4 colorVarying;

uniform float translate;

void main()
{
	gl_Position = position;
	gl_Position.y += sin(translate) / 2.0;
	
	colorVarying = color;
}
