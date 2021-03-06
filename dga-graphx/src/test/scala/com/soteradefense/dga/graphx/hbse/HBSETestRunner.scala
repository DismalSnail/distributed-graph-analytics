
/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.soteradefense.dga.graphx.hbse

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import org.apache.spark.graphx.Graph
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

class HBSETestRunner extends AbstractHBSERunner {

  override type H = (RDD[(Long, Double)], Graph[HBSEData, Long])
  override type S = Graph[HBSEData, Long]

  override def save[ED: ClassTag](betweennessSet: RDD[(Long, Double)], graph: Graph[HBSEData, ED]): H = (betweennessSet, save(graph))

  override def save[VD: ClassTag, ED: ClassTag](graph: Graph[VD, ED]): S = graph.asInstanceOf[Graph[HBSEData, Long]]

  override def write(kryo: Kryo, output: Output): Unit = {
    // Nothing needs to be serialized
  }

  override def read(kryo: Kryo, input: Input): Unit = {
    // Nothing needs to be read.
  }
}
