/**
 * Copyright (C) 2012 https://github.com/tenderowls/haxemojos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tenderowls.opensource.haxemojos.components;

import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;

/**
 * Unpack haxe and neko artifacts into local repository.
 */
@Component(role = AbstractMavenLifecycleParticipant.class)
public class HaxeLifecycleParticipant extends AbstractMavenLifecycleParticipant {

    @Requirement
    private NativeBootstrap bootstrap;

    @Override
    public void afterProjectsRead(MavenSession session) throws MavenExecutionException
    {
        super.afterProjectsRead(session);

        try
        {
            MavenProject project = session.getCurrentProject();
            bootstrap.initialize(project, session.getLocalRepository());
        }
        catch (Exception e)
        {
            throw new MavenExecutionException(e.getMessage(), e);
        }
    }
}
