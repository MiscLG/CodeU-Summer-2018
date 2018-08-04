/*Copyright 2017 Google Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

/*
  the object tags contains the relevant information for every tag that will be
  allowed on the chat application, the attributes bit is not necessary on this document
  at the moment
*/
const defaultAttributes = ["class", "id", "style"];
const tags = {
  div: {isButton: false, attributes: defaultAttributes},
  span: {isButton: false, attributes: defaultAttributes},
  style: {isButton: false, attributes: defaultAttributes},
  strong: {isButton: true, attributes: defaultAttributes, buttonText:"B"},
  em: {isButton: true, attributes: defaultAttributes, buttonText:"I"},
  del: {isButton: true, attributes: defaultAttributes, buttonText:"S"},
  mark: {isButton: true, attributes: defaultAttributes, buttonText:"M"},
  sub: {isButton: true, attributes: defaultAttributes,  buttonText:"x"},
  sup: {isButton: true, attributes: defaultAttributes, buttonText:"x"},
  h1: {isButton: false, attributes: defaultAttributes},
  h2: {isButton: false, attributes: defaultAttributes},
  h3: {isButton: false, attributes: defaultAttributes},
  h4: {isButton: false, attributes: defaultAttributes},
  h5: {isButton: false, attributes: defaultAttributes},
  h6: {isButton: false, attributes: defaultAttributes},
  blockquote: {isButton: false, attributes: [...defaultAttributes,"cite"]},
  cite: {isButton: false, attributes: defaultAttributes},
  dfn: {isButton: false, attributes: defaultAttributes},
  a: {isButton: false, attributes: [...defaultAttributes,"href"]},
  img: {isButton: false, attributes: [...defaultAttributes,"align","src","width","height","alt"]},
  iframe:{isButton: false, attributes: [...defaultAttributes,"align","src","width","height","allow","allowfullscreen"]},
  code: {isButton: false, attributes: defaultAttributes},
  samp: {isButton: false, attributes: defaultAttributes},
};


// function to add html tags to the chat input field once buttons have been pressed
function addButtonText(tag) {
  let newtext = "<"+tag+"> </" +tag+">";
  document.getElementById('status_name').value += newtext;
}
//adds working buttons to input field to avoid hard-coding
function addButtons() {
  let styleBar = document.getElementById("bar");
  let child = document.getElementById("status_name");
  //section creates a working button for every item on tags
  for(let tag in tags){
    if(tags[tag]["isButton"]){
      let button = document.createElement("button");
      button.setAttribute("type","button");
      button.setAttribute("class","button");
      button.setAttribute("id",tag);
      button.setAttribute("onclick","addButtonText('"+tag+"')");
      let innerTag = document.createElement(tag);
      let innerTagText = document.createTextNode(tags[tag]["buttonText"]);
      innerTag.appendChild(innerTagText);
      button.appendChild(innerTag);
      styleBar.appendChild(button);
      styleBar.insertBefore(button,styleBar.childNodes[styleBar.childElementCount-1]);
    }
  }
}
function callFunctions(){
  addButtons();
}
