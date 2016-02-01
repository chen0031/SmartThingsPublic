definition(
    name: "CPFSecondSmartApp",
    namespace: "CPF",
    author: "CPF",
    description: "Turn a switch on when motion is detected, and turn it off when motion stops.",
    category: "My Apps",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")
preferences {
	section("Turn on this switch") {
		// TODO: put inputs here
        input "theswitch", "capability.switch", required: true, multiple: false
    //    input "thedoor", "capability.doorControl", required: true, multiple: false
	}
    section("Set the dimmer when switch is turned on") {
    	input "lightSensor", "capability.illuminanceMeasurement", required: true, multiple: false
        input "light", "capability.switch"
   // input "lightSensor", "capability.illuminanceMeasurement"
    }
    section("Close this door"){
       input "thedoor", "capability.doorControl", required: true, multiple: false
      //input "lock", "capability.lock", required: true, multiple: false
      // input "door", "capability.contactSensor", required: true, multiple: false
     //   input "thedoor", "capability.doorControl"
    }
   // section("Title"){
    //    input "light", "capability.switch"
    //}
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	// TODO: subscribe to attributes, devices, locations, etc.
    subscribe(theswitch, "switch.on", switchOnHandler)
    //subsribe(thelight, "illuminance", lightIlluminanceHandler)
    //subsribe
}

// TODO: implement event handlers
def switchOnHandler(evt){
	log.debug "switchOnHandler called: $evt"
  //  def lightStatus = thelight.supportedilluminanceMeasurement
    lightSensor.integerValue = 10
    log.debug "lightIlluminance is set to: 10"
    log.debug "check if lightIlluminance is 10"
    if (lightSensor.integerValue == 10) {
    	thedoor.close()
       log.debug "the door is closed"
    }
}