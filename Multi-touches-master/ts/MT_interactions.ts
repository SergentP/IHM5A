import { FSM } from "./FSM";
import * as transfo from "./transfo";

function multiTouch(element: HTMLElement) : void {
    let pointerId_1 : number, Pt1_coord_element : SVGPoint, Pt1_coord_parent : SVGPoint,
        pointerId_2 : number, Pt2_coord_element : SVGPoint, Pt2_coord_parent : SVGPoint,
        originalMatrix : SVGMatrix,
        getRelevantDataFromEvent = (evt : TouchEvent) : Touch => {
            for(let i=0; i<evt.changedTouches.length; i++) {
                let touch = evt.changedTouches.item(i);
                if(touch.identifier === pointerId_1 || touch.identifier === pointerId_2) {
                    return touch;
                }
            }
            return null;
        };
    pointerId_2 = null; // l.88: we must initialize pointeurId_2 at null in order to be able to rotozoom for the first time
    enum MT_STATES {Inactive, Translating, Rotozooming}
    let fsm = FSM.parse<MT_STATES>( {
        initialState: MT_STATES.Inactive,
        states: [MT_STATES.Inactive, MT_STATES.Translating, MT_STATES.Rotozooming],
        transitions : [
            { from: MT_STATES.Inactive, to: MT_STATES.Translating, // first touch on the screen
                eventTargets: [element],
                eventName: ["touchstart"],
                useCapture: false,
                action: (evt : TouchEvent) : boolean => {
                    pointerId_1 = evt.changedTouches.item(0).identifier;
                    const touch = getRelevantDataFromEvent(evt);
                    originalMatrix = transfo.getMatrixFromElement(element);
                    Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                    Pt1_coord_element = Pt1_coord_parent.matrixTransform(originalMatrix.inverse());
                    return true;
                }
            },
            { from: MT_STATES.Translating, to: MT_STATES.Translating, // any drag after the first touch and before the second
                eventTargets: [document],
                eventName: ["touchmove"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    evt.preventDefault();
                    evt.stopPropagation();
                    if (pointerId_1 !== null) {
                        const touch = getRelevantDataFromEvent(evt);
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        transfo.drag(element, originalMatrix, Pt1_coord_element, Pt1_coord_parent);
                    } else if (pointerId_2 !== null) {
                        const touch = getRelevantDataFromEvent(evt);
                        Pt2_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        transfo.drag(element, originalMatrix, Pt2_coord_element, Pt2_coord_parent);
                    }
                    originalMatrix = transfo.getMatrixFromElement(element);
                    return true;
                }
            },
            { from: MT_STATES.Translating,
                to: MT_STATES.Inactive, // release of the last touch on the screen
                eventTargets: [document],
                eventName: ["touchend"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    const touch = getRelevantDataFromEvent(evt);
                    if(touch.identifier === pointerId_1) {
                        pointerId_1 = null;
                        originalMatrix = null;
                        Pt1_coord_element = null;
                        Pt1_coord_parent = null;
                    } else if (touch.identifier === pointerId_2) {
                        pointerId_2 = null;
                        originalMatrix = null;
                        Pt2_coord_element = null;
                        Pt2_coord_parent = null;
                    }
                    return true;
                }
            },
            { from: MT_STATES.Translating, to: MT_STATES.Rotozooming, // second touch on the screen
                eventTargets: [element],
                eventName: ["touchstart"],
                useCapture: false,
                action: (evt : TouchEvent) : boolean => {
                    if (pointerId_1 === null) {
                        pointerId_1 = evt.changedTouches.item(0).identifier;
                        const touch = getRelevantDataFromEvent(evt);
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        Pt1_coord_element = Pt1_coord_parent.matrixTransform(originalMatrix.inverse());
                        return true;
                    } else if (pointerId_2 === null) {
                        pointerId_2 = evt.changedTouches.item(0).identifier;
                        const touch = getRelevantDataFromEvent(evt);
                        Pt2_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        Pt2_coord_element = Pt2_coord_parent.matrixTransform(originalMatrix.inverse());
                        return true;
                    }
                }
            },
            { from: MT_STATES.Rotozooming, to: MT_STATES.Rotozooming, // any drag of any of the two first touch on the screen
                eventTargets: [document],
                eventName: ["touchmove"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    evt.preventDefault();
                    evt.stopPropagation();
                    const touch = getRelevantDataFromEvent(evt);
                    if (touch.identifier === pointerId_1) {
                        Pt1_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        transfo.rotozoom(element, originalMatrix, Pt1_coord_element, Pt1_coord_parent, Pt2_coord_element, Pt2_coord_parent);
                    } else if (touch.identifier === pointerId_2) {
                        Pt2_coord_parent = transfo.getPoint(touch.clientX, touch.clientY);
                        transfo.rotozoom(element, originalMatrix, Pt2_coord_element, Pt2_coord_parent, Pt1_coord_element, Pt1_coord_parent);
                    }
                    originalMatrix = transfo.getMatrixFromElement(element);
                    return true;
                }
            },
            { from: MT_STATES.Rotozooming,
                to: MT_STATES.Translating, // release of the penultimate touch on the screen
                eventTargets: [document],
                eventName: ["touchend"],
                useCapture: true,
                action: (evt : TouchEvent) : boolean => {
                    const touch = getRelevantDataFromEvent(evt);
                    if(touch.identifier === pointerId_1) {
                        // this is the reason why most of the transitions have an if statement to check if pointerId_1 is/isn't null
                        // and an else if to check if pointerId_2 is/isn't null so that no third touch is taken into account
                        pointerId_1 = null;
                        Pt1_coord_element = null;
                        Pt1_coord_parent = null;
                    } else if (touch.identifier === pointerId_2) {
                        pointerId_2 = null;
                        Pt2_coord_element = null;
                        Pt2_coord_parent = null;
                    }
                    return true;
                }
            }
        ]
    } );
    fsm.start();
}

//______________________________________________________________________________________________________________________
//______________________________________________________________________________________________________________________
//______________________________________________________________________________________________________________________
function isString(s : any) : boolean {
    return typeof(s) === "string" || s instanceof String;
}

export let $ = (sel : string | Element | Element[]) : void => {
    let L : Element[] = [];
    if( isString(sel) ) {
        L = Array.from( document.querySelectorAll(<string>sel) );
    } else if(sel instanceof Element) {
        L.push( sel );
    } else if(sel instanceof Array) {
        L = sel;
    }
    L.forEach( multiTouch );
};
