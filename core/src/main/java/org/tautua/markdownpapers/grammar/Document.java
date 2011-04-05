/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tautua.markdownpapers.grammar;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Represents a markdown document</p>
 *
 * @author Larry Ruiz
 */
public class Document extends SimpleNode {
    private Map<String, Resource> namedResources = new HashMap<String, Resource>();

    public Document(int id) {
        super(id);
    }

    @Override
    protected Document getDocument() {
        return this;
    }

    public Resource findResourceByName(String name) {
        return namedResources.get(name);
    }

    @Override
    public void jjtAddChild(Node n, int i) {
        if (n instanceof NamedResource) {
            NamedResource r = (NamedResource) n;
            namedResources.put(r.getId(), r.getResource());
        }

        super.jjtAddChild(n, i);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

