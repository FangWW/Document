//
//  Shader.fsh
//  glTestingGround
//
//  Created by David Jacobs on 3/8/10.
//  Copyright Stanford University 2010. All rights reserved.
//

varying lowp vec4 colorVarying;

void main()
{
	gl_FragColor = colorVarying;
}
