// Custom code panel
// main function is the default method executed after processing current module
function main() {
  console.log(
    new Date(),
    "|MSISDN",
    $MSISDN,
    "|ResponseCode:",
    $response.responseCode,
    "|ServiceClass:",
    $response.serviceClassCurrent,
    "|SubscriberInput:",
    $subscriberInput
  );

  try {
    var offerInfo553 = _parseOfferInformationList(
      $response.offerInformationList,
      "553"
    );
    var offerInfo552 = _parseOfferInformationList(
      $response.offerInformationList,
      "552"
    );
    var offerInfo554 = _parseOfferInformationList(
      $response.offerInformationList,
      "554"
    );

    // STEP 1: Pre-USE Condition, DORMANT conditions
    console.log("offerInformationList", $response.offerInformationList);
    console.log(
      "chec k status",
      _checkOfferIDExists($response.offerInformationList, "552") &&
        _getTime(offerInfo552.expiryDateTime) >= _now()
    );
    if (
      $response.responseCode == 126 ||
      (_checkOfferIDExists($response.offerInformationList, "552") &&
        _getTime(offerInfo552.expiryDateTime) >= _now()) ||
      (_checkOfferIDExists($response.offerInformationList, "553") &&
        _getTime(offerInfo553.expiryDateTime) >= _now()) ||
      _length($response.serviceClassCurrent) != 4
    ) {
      console.log("satisfied dormant conditions");
      if ($subscriberInput == "135*3") {
        setResponseCode("V3013");
        return "567fac9";
      } else if (
        $subscriberInput == "135*005" ||
        $subscriberInput == "135*120"
      ) {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "222") {
        setResponseCode("V3008");
        return "567fac9";
      } else if ($subscriberInput == "135*79") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*121") {
        setResponseCode("V3013");
        return "567fac9";
      } else if ($subscriberInput == "135*122") {
        setResponseCode("V3013");
        return "567fac9";
      } else if ($subscriberInput == "135*107") {
        setResponseCode("V3013");
        return "567fac9";
      } else if ($subscriberInput == "135*112") {
        setResponseCode("V3013");
        return "567fac9";
      } else if ($subscriberInput == "135*113") {
        setResponseCode("V3013");
        return "567fac9";
      } else if ($subscriberInput == "101*15") {
        if (
          (_checkOfferIDExists($response.offerInformationList, "552") &&
            _getTime(offerInfo552.expiryDateTime) >= _now()) ||
          (_checkOfferIDExists($response.offerInformationList, "553") &&
            _getTime(offerInfo553.expiryDateTime) >= _now())
        ) {
          console.log("offer 553/552");
          setResponseCode("V3015");
          return "567fac9";
        }
        if ($response.responseCode == 126) {
          return "2f66d66"; // get account details ID
        }
      } else if ($subscriberInput == "135*800") {
        setResponseCode("V3023");
        return "567fac9"; //app end id
      } else if ($subscriberInput == "111*100") {
        setResponseCode("V3023");
        return "567fac9";
      } else if (
        $subscriberInput == "135*130" ||
        $subscriberInput == "135*175" ||
        $subscriberInput == "135*123" ||
        $subscriberInput == "135*125"
      ) {
        setResponseCode("V3015");
        return "567fac9";
      } else if (
        $subscriberInput == "135*160" ||
        $subscriberInput == "135*140" ||
        $subscriberInput == "135*60"
      ) {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*20") {
        setResponseCode("V3018");
        return "567fac9";
      } else if ($subscriberInput == "135*11" || $subscriberInput == "135*13") {
        setResponseCode("V3001");
        return "567fac9";
      } else if ($subscriberInput == "135*250") {
        setVariable("respCode", "E2008");
        setVariable(
          "message",
          "Sorry,your line isn't active. Please activate the line first by calling 135 to be able to use this service"
        );
        return "32434e1";
      } else if ($subscriberInput == "135*222") {
        console.log(
          _now() +
            " : " +
            offerInfo553.offerID +
            " : " +
            offerInfo553.expiryDateTime
        );
        if (
          (offerInfo552.offerID != -1 &&
            _getTime(offerInfo552.expiryDateTime) >= _now()) ||
          (offerInfo553.offerID != -1 &&
            _getTime(offerInfo553.expiryDateTime) >= _now())
        ) {
          setVariable("respCode", "E2007");
          setVariable(
            "message",
            "There seems to be some technical error. Please wait for some time and then try to activate the promotion. Dial *135*222# to activate the promotion"
          );
          return "32434e1";
        }
      } else {
        setResponseCode("V3001"); // preuse
        return "567fac9";
      }
    }

    // STEP 2: SIM Expired
    if (
      $response.responseCode == 0 &&
      _getTime($response.supervisionExpiryDate) < _now()
    ) {
      if ($subscriberInput == "135*222") {
        // SIM Expired
        setVariable("respCode", "E2007");
        setVariable(
          "message",
          "There seems to be some technical error. Please wait for some time and then try to activate the promotion. Dial *135*222# to activate the promotion"
        );
        return "32434e1";
      } else if (
        $subscriberInput == "135*005" ||
        $subscriberInput == "135*120"
      ) {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "101*15") {
        setResponseCode("V3016");
        return "567fac9";
      } else if ($subscriberInput == "135*79") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*121") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*122") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*107") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*112") {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*113") {
        setResponseCode("V3014");
        return "567fac9";
      } else if (
        $subscriberInput == "135*160" ||
        $subscriberInput == "135*140" ||
        $subscriberInput == "135*60"
      ) {
        setResponseCode("V3014");
        return "567fac9";
      } else if ($subscriberInput == "135*800") {
        setResponseCode("V3023");
        return "567fac9";
      } else if ($subscriberInput == "135*20") {
        setResponseCode("V3018");
      } else if (
        $subscriberInput == "135*130" ||
        $subscriberInput == "135*175" ||
        $subscriberInput == "135*123" ||
        $subscriberInput == "135*125"
      ) {
        setResponseCode("V3015");
        return "567fac9";
      } else if ($subscriberInput == "135*11" || $subscriberInput == "135*13") {
        setResponseCode("V3001");
        return "567fac9";
      } else if ($subscriberInput == "222") {
        setResponseCode("V3008");
        return "567fac9";
      } else if (
        $subscriberInput == "135*246" ||
        $subscriberInput == "135*250"
      ) {
        setVariable("respCode", "E2008");
        setVariable(
          "message",
          "Sorry,your line isn't active. Please activate the line first by calling 135 to be able to use this service"
        );
        return "32434e1";
      } else {
        setResponseCode("V3002");
        return "567fac9";
      }
    }
    // STEP 3: Query Product Type
    if (!_contains($response.serviceClassCurrent, $ProductType)) {
      switch ($subscriberInput) {
        case "135*41":
        case "135*42":
        case "135*43":
        case "135*44":
          setVariable(
            "subMsg",
            "Dear customer. You have failed to subscribe to the requested bundle. Dial 188 for more information."
          );
          //setResponseCode("V3004");
          break;

        case "135*130":
        case "135*175":
        case "135*35":
        case "135*125":
        case "135*123":
          setResponseCode("V3016");
          return "567fac9";
        case "135*121":
          setResponseCode("V3016");
          return "567fac9";
        case "135*122":
          setResponseCode("V3016");
          return "567fac9";
        case "135*79":
          setVariable(
            "subMsg",
            "This offer is currently not available for you. For the best deals on voice and data, dial *135# or *055#."
          );
          setResponseCode("V3012");
          return "567fac9";
        case "135*107":
          setVariable(
            "subMsg",
            "You are not eligible to activate the FREE Youtube Hours offers. To get the best deals on voice & data for you dial *055# or *135# to choose your preferred bundle & subscribe now."
          );
          setResponseCode("V3012");
          return "567fac9";
        case "135*112":
          setResponseCode("V3016");
          return "567fac9";
        case "135*113":
          setResponseCode("V3016");
          return "567fac9";
        case "135*11":
        case "135*13":
          setResponseCode("V3020");
          return "567fac9";

        case "101*15":
          setResponseCode("V3015");
          return "567fac9";
        case "135*800":
          setResponseCode("E2010");
          return "567fac9";
        case "135*160":
        case "135*140":
        case "135*120":
          setResponseCode("V3009");
          return "567fac9";

        case "135*60":
          setResponseCode("V3019");
          return "567fac9";

        case "111*100":
          setVariable(
            "subMsg",
            "To migrate to Flexi Prepaid Plan or Easy Prepaid Plan, please migrate to Pay as you go line and then dial *111*100#"
          );
          break;

        case "135*222":
          setVariable("respCode", "E2009");
          setVariable(
            "message",
            "Unfortunately you are not eligible for this special promotion. We have great offers for you. Please dial *055# to know your best deals"
          );
          return "32434e1";

        case "135*246":
          setVariable("respCode", "E2010");
          setVariable(
            "message",
            "Sorry, you're not eligible to activate this promotion. For more info, visit www.du.ae"
          );
          return "32434e1";
        case "222":
          setResponseCode("V3008");
          return "567fac9";

        case "135*225":
        case "135*422":
        case "135*433":
          setVariable(
            "subMsg",
            "Hi! Thank you for your interest, unfortunately the offer is not available on your line. To find out more about other offers which you can avail of, visit www.du.ae"
          );
          //setResponseCode("V3011");
          break;
        case "800":
          setVariable(
            "subMsg",
            "Play On Demand data service is not available for your plan"
          );
          //setResponseCode("V3005");
          break;
        case "555":
          setVariable(
            "subMsg",
            "START STOP Data service is not available for your plan"
          );
          //setResponseCode("V3006");
          break;
        case "135*250":
          setVariable(
            "subMsg",
            "Sorry, Your current rate plan is not eligible to this offer, please migrate to one of eligible rate plans"
          );
          break;
        case "135*005":
          setVariable(
            "subMsg",
            "Your current rate plan is not eligible for this bundle. Please migrate to an eligible rate plan to be able to subscribe to the bundle."
          );
          //setResponseCode("V3007");
          break;
        case "135*100":
        case "135*150":
          setVariable("subMsg", "Your line is not eligible for this service.");
          break;
        default:
          setVariable(
            "subMsg",
            "You are not eligible for this promotion. For more info, visit www.du.ae"
          );
        //setResponseCode("V3003");
      }
      return "97a74f1";
    }

    //CR-7152 Mass IDD
    if ($subscriberInput == "135*125") {
      if ($response.serviceClassCurrent == "1010") {
        return "e82c7d9";
      }
    }
    if ($subscriberInput == "135*79") {
      if ($response.serviceClassCurrent == "1010") {
        return "e82c7d9";
      } else if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1011" ||
        $response.serviceClassCurrent == "1002"
      ) {
        setVariable("subscriberInput", "1352*1");
        return "4a19e38";
      }
    }

    //Pay As You Go DataLine + Consumer IOT
    if (
      _contains($response.serviceClassCurrent, "1006") &&
      _checkOfferIDExists($response.offerInformationList, 1290)
    ) {
      if (
        $subscriberInput == "135*250" ||
        $subscriberInput == "135*35" ||
        $subscriberInput == "135*85" ||
        $subscriberInput == "135*75"
      ) {
        setResponseCode("V3008");
        return "567fac9";
      }
    }

    //MAP Direct Alias Code of Migration CON-5838
    //135*1049 AllStarV1D1
    if ($subscriberInput == "135*1049") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*2*2*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*2*2*3");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '111*4*1*1*1');
              }*/
    }
    //135*1079 AllStarV1D2
    if ($subscriberInput == "135*1079") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*3*2*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*3*2*3");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '111*4*1*2*1');
              }*/
    }
    //135*1099 AllStarV1D3
    if ($subscriberInput == "135*1099") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*4*2*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*4*2*3");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '111*4*1*3*1');
              }*/
    }
    //135*1119 AllStarV3D3
    if ($subscriberInput == "135*1119") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*5*3*4*4*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*4*4*4");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '111*4*1*3*3');
              }*/
      /*}*/
    }

    //135*2049 AllStarV1D1Annual
    if ($subscriberInput == "135*2049") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*2*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*2*2");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '112*8*3*1*1');
              }*/
    }
    //135*2079 AllStarV1D2Annual
    if ($subscriberInput == "135*2079") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*3*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*3*2");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '112*8*3*2*1');
              }*/
    }
    //135*2099 AllStarV1D3Annual
    if ($subscriberInput == "135*2099") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*4*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*4*2");
      }
      /*else if (($response.serviceClassCurrent == "1002")) {
              setVariable('subscriberInput', '112*8*3*3*1');
              }*/
    }
    //135*1139 AllStarV1D4Monthly
    if ($subscriberInput == "135*1139") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*4*3*5*2*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*5*2*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*4*1*5*2');
              }*/
    }

    //135*1169 AllStarV4D4Monthly
    if ($subscriberInput == "135*1169") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*4*3*4*4*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*4*4*2");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*4*1*5*5');
              }*/
      /*}*/
    }

    //135*2139 AllStarV1D4Annual
    if ($subscriberInput == "135*2139") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*5*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*5*2");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*8*1*5*2');
              }*/
    }

    //135*2059 AllStarV2D1Annual
    if ($subscriberInput == "135*2059") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*2*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*2*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*8*3*1*2');
              }*/
    }
    //135*1059 AllStarV2D1
    if ($subscriberInput == "135*1059") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*2*3*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*2*3*4");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '111*4*1*1*2');
              }*/
    }
    //135*2089 AllStarV2D2Annual
    if ($subscriberInput == "135*2089") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*3*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*3*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*8*3*2*2');
              }*/
    }
    //135*1089 AllStarV2D2
    if ($subscriberInput == "135*1089") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*3*3*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*3*3*4");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '111*4*1*2*2');
              }*/
    }
    //135*2109 AllStarV2D3Annual
    if ($subscriberInput == "135*2109") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*4*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*4*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*8*3*3*2');
              }*/
    }
    //135*1109 AllStarV2D3
    if ($subscriberInput == "135*1109") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*5*3*4*3*4");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*4*3*4");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '111*4*1*3*2');
              }*/
    }
    //135*2149 AllStarV2D4Annual
    if ($subscriberInput == "135*2149") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*6*1*5*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*7*1*5*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*8*3*4*2');
              }*/
    }
    //135*1149 AllStarV2D4
    if ($subscriberInput == "135*1149") {
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "112*4*3*5*3*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "112*4*2*5*3*3");
      }
      /*else if ($response.serviceClassCurrent == "1002") {
                  setVariable('subscriberInput', '112*4*1*4*2');
              }*/
    }
    //135*2069 AllStarV3D1Annual
    if ($subscriberInput == "135*2069") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*6*3*1*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*1*3");
      }
      /*}*/
    }
    //135*1069 AllStarV3D1
    if ($subscriberInput == "135*1069") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*5*3*1*3*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*1*3*2");
      }
      /*}*/
    }
    //135*2999 AllStarV3D2Annual
    if ($subscriberInput == "135*2999") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*6*3*2*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*2*3");
      }
      /*}*/
    }
    //135*1999 AllStarV3D2
    if ($subscriberInput == "135*1999") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*5*3*2*3*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*2*3*2");
      }
      /*}*/
    }
    //135*2119 AllStarV3D3Annual
    if ($subscriberInput == "135*2119") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*6*3*3*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*3*3");
      }
      /*}*/
    }
    //135*2159 AllStarV3D4Annual
    if ($subscriberInput == "135*2159") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*6*3*4*3");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*4*3");
      }
      /*}*/
    }
    //135*1159 AllStarV3D4
    if ($subscriberInput == "135*1159") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*4*3*3*2*1");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*3*2*1");
      }
      /*}*/
    }
    //135*4079 AllStarV4D1Annual
    if ($subscriberInput == "135*4079") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*1*3*1*4*1");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*1*4");
      }
      /*}*/
    }
    //135*4109 AllStarV4D2Annual
    if ($subscriberInput == "135*4109") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*1*3*2*4*1");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*2*4");
      }
      /*}*/
    }
    //135*2129 AllStarV4D3Annual
    if ($subscriberInput == "135*2129") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*1*3*3*4*1");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*3*4");
      }
      /*}*/
    }
    //135*2169 AllStarV4D4Annual
    if ($subscriberInput == "135*2169") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*1*3*4*4*1");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*7*3*4*4");
      }
      /*}*/
    }
    //135*3079 AllStarV4D1
    if ($subscriberInput == "135*3079") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*4*3*1*4*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*1*4*2");
      }
      /*}*/
    }
    //135*3109 AllStarV4D2
    if ($subscriberInput == "135*3109") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*4*3*2*4*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*2*4*2");
      }
      /*}*/
    }
    //135*1129 AllStarV4D3
    if ($subscriberInput == "135*1129") {
      //if (_checkOfferIDExists($response.offerInformationList,3132)){
      if ($response.serviceClassCurrent == "1011") {
        setVariable("subscriberInput", "1111*4*3*3*4*2");
      } else if ($response.serviceClassCurrent == "1001") {
        setVariable("subscriberInput", "1111*4*2*3*4*2");
      }
      /*}*/
    }
    //<<<<Sec: CR-6540 Data Passes -- Start>>>>
    //Direct for Music Pass
    if ($subscriberInput == "135*1501" || $subscriberInput == "135*2501") {
      //CR-6955 Restrict PAYG and WOW Music Passes
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1011"
      ) {
        setVariable(
          "subMsg",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
        setResponseCode("V3012");
        return "567fac9";
      } else if ($response.serviceClassCurrent == "1002") {
        setVariable(
          "subMsg",
          "Please go to *055# or *135# to see any new offers"
        );
        setResponseCode("V3012");
        return "567fac9";
      } else if (
        $response.serviceClassCurrent == "1010" ||
        $response.serviceClassCurrent == "1004"
      ) {
        setVariable("subscriberInput", "777*1501");
      } else {
        setVariable(
          "subMsg",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //Direct for Chat Pass
    if ($subscriberInput == "135*1502" || $subscriberInput == "135*2502") {
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1010" ||
        $response.serviceClassCurrent == "1011" ||
        $response.serviceClassCurrent == "1004"
      ) {
        setVariable("subscriberInput", "777*1502");
      } else if ($response.serviceClassCurrent == "1002") {
        setVariable(
          "subMsg",
          "Please go to *055# or *135# to see any new offers"
        );
        setResponseCode("V3012");
        return "567fac9";
      } else {
        setVariable(
          "subMsg",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //Direct for Social Pass
    if ($subscriberInput == "135*1503") {
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1010" ||
        $response.serviceClassCurrent == "1011" ||
        $response.serviceClassCurrent == "1004"
      ) {
        setVariable("subscriberInput", "777*1503");
      } else if ($response.serviceClassCurrent == "1002") {
        setVariable(
          "subMsg",
          "Please go to *055# or *135# to see any new offers"
        );
        setResponseCode("V3012");
        return "567fac9";
      } else {
        setVariable(
          "subMsg",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //Direct for Video Pass
    if ($subscriberInput == "135*1504") {
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1010" ||
        $response.serviceClassCurrent == "1011" ||
        $response.serviceClassCurrent == "1004"
      ) {
        setVariable("subscriberInput", "777*1504");
      } else if ($response.serviceClassCurrent == "1002") {
        setVariable(
          "subMsg",
          "Please go to *055# or *135# to see any new offers"
        );
        setResponseCode("V3012");
        return "567fac9";
      } else {
        setVariable(
          "subMsg",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //<<<<Sec: CR-6540 Data Passes -- End>>>>

    //<<<<Sec: CR-7189 Alo Happiness -- Start>>>>
    if ($subscriberInput == "135*67") {
      if (
        _checkOfferIDExists($response.offerInformationList, 4503) &&
        $response.serviceClassCurrent == "1002"
      ) {
        setVariable("subscriberInput", "777*677");
      } else {
        setVariable(
          "subMsg",
          "This offer is currently not available for you. For the best deals on voice and data, dial *135# or *055#."
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //<<<<Sec: CR-7189 Alo Happiness -- End>>>>

    //CR-6975 ALO Restriction
    if ($subscriberInput == "111*100") {
      console.log("\nCR-6975");
      if ($response.serviceClassCurrent == "1002") {
        setVariable(
          "subMsg",
          "Please go to *055# or *135# to see any new offers"
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }

    //CR-6910_D4_Direct_USSD_2022
    if ($subscriberInput == "135*28") {
      return "2f66d66";
    }

    //CR-7112 Acquisition Offer 2
    if ($subscriberInput == "135*030") {
      return "2f66d66";
    }

    //CR-7113 Acquisition Offer 3
    if ($subscriberInput == "135*040") {
      return "2f66d66";
    }

    //CR-7103 Acquisition Offer 1

    if ($subscriberInput == "135*090") {
      if (_checkOfferIDExists($response.offerInformationList, "6789")) {
        setVariable("subscriberInput", "777*090");
        return "4a19e38";
      } else {
        setVariable(
          "subMsg",
          "This offer is currently not available for you. For the best deals on voice and data, dial *135# or *055#."
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }

    //CR-6842 SpeedBooster
    if ($subscriberInput == "135*127") {
      if ($response.serviceClassCurrent == "1002") {
        setVariable("subscriberInput", "777*127");
      } else {
        setResponseCode("V3016");
        return "567fac9";
      }
    }

    //CR-7750
    if ($subscriberInput == "135*222") {
      setVariable("subscriberInput", "1352*1");
    }
    if ($subscriberInput == "135*3839") {
      setVariable("subscriberInput", "135*222");
    }

    //CR-7657 Ramadan Offer
    if (
      $subscriberInput == "135*210" ||
      $subscriberInput == "135*211" ||
      $subscriberInput == "135*212"
    ) {
      if (
        _getTime($response.supervisionExpiryDate) < _now() ||
        (_checkOfferIDExists($response.offerInformationList, "552") &&
          _getTime(offerInfo552.expiryDateTime) >= _now()) ||
        (_checkOfferIDExists($response.offerInformationList, "553") &&
          _getTime(offerInfo553.expiryDateTime) >= _now())
      ) {
        //Invalid SIM State
        setResponseCode("V3013");
        return "567fac9";
      } else {
        //Ramadan Offer Period Check
        var currentDate = new Date();
        var endTime = new Date("2024-04-10T23:59:59");
        if (currentDate <= endTime) {
          if ($subscriberInput == "135*210") {
            if ($response.serviceClassCurrent == "1010") {
              //Success, redirect to USSD DM --> 778*3 (RamadanData 1)
              setVariable("subscriberInput", "778*3");
            } else {
              setVariable("subMsg", "You are not eligible for the service.");
              setResponseCode("V3012");
              return "567fac9";
            }
          }
          if ($subscriberInput == "135*211" || $subscriberInput == "135*212") {
            //Eligible Service Class Check
            if (
              $response.serviceClassCurrent == "1001" ||
              $response.serviceClassCurrent == "1002" ||
              $response.serviceClassCurrent == "1010" ||
              $response.serviceClassCurrent == "1011"
            ) {
              if ($response.serviceClassCurrent != "1010") {
                //Except AllStar, all other SCs must have whitelisting offer 4401
                if (_checkOfferIDExists($response.offerInformationList, 4401)) {
                  //Success, redirect to USSD DM --> 778*4 (RamadanData 2)
                  setVariable("subscriberInput", "778*4");
                } else {
                  setVariable(
                    "subMsg",
                    "You are not eligible for the service."
                  );
                  setResponseCode("V3012");
                  return "567fac9";
                }
              } else {
                //For AllStar, no need to have whitelisting offer 4401
                //Success, redirect to USSD DM --> 778*4 (RamadanData 2)
                setVariable("subscriberInput", "778*4");
              }
            } else {
              setVariable("subMsg", "You are not eligible for the service.");
              setResponseCode("V3012");
              return "567fac9";
            }
          }
        } else {
          setVariable(
            "subMsg",
            "This offer is currently not available. For the best deals on voice and data, dial *135# or *055#."
          );
          setResponseCode("V3012");
          return "567fac9";
        }
      }
    }
    //End CR-7657 Ramadan Offer
    //CR-7510 Labour Solo Geo 1
    if ($subscriberInput == "300") {
      //console.log("\nInside 7510");
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1002" ||
        $response.serviceClassCurrent == "1011"
      ) {
        //console.log("\nInside 7510---> Redirecting 778*5");
        setVariable("subscriberInput", "778*5");
        if ($response.serviceClassCurrent == "1002") {
          setVariable(
            "subMsg",
            "Thank you for your interest in All in One Pack\n\nFor 30 days you will enjoy\n- Unlimited data in your camp area at 3 Mbps\n- 30GB data (1GB per day) at full speed to use all over the UAE. The 1 GB data is renewed every 24 hours.\n\nUnlimited data will work only on 4G & 5G network, please ensure the eligibility of your camp area by visiting alo.ae/geo\n\nTo activate now dial *300#"
          );
        } else {
          setVariable(
            "subMsg",
            "Thank you for your interest in All in One Pack\n\nFor 30 days you will enjoy\n- Unlimited data in your camp area at 3 Mbps\n- 30GB data (1GB per day) at full speed to use all over the UAE. The 1 GB data is renewed every 24 hours.\n\nUnlimited data will work only on 4G & 5G network, please ensure the eligibility of your camp area by visiting du.ae/geo\n\nTo activate now dial *300#"
          );
        }
        return "32434e1";
      }
    }
    //End CR-7510 Labour Solo Geo 1

    //CR5948
    if ($subscriberInput == "135*107") {
      var today = new Date();
      var dateStr =
        today.getMonth() +
        1 +
        "/" +
        today.getDate() +
        "/" +
        today.getFullYear();
      const [month1, day1, year1] = dateStr.split("/");
      const dateyt = new Date(+year1, month1 - 1, +day1);
      const startStr = "06/22/2022";
      const [month2, day2, year2] = startStr.split("/");
      const startDateyt = new Date(+year2, month2 - 1, +day2);
      const endStr = "12/01/2022";
      const [month3, day3, year3] = endStr.split("/");
      const endDateyt = new Date(+year3, month3 - 1, +day3);
      if (dateyt >= startDateyt && dateyt <= endDateyt) {
        setVariable("subscriberInput", "135*193");
      } else {
        setVariable(
          "subMsg",
          "The FREE Youtube Hours offer is no longer available. Stay connected with the best deals on voice and data. Dial *055# or *135# to choose your preferred bundle and subscribe now."
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //CR5948
    //CR7013
    //CR-6975 D2
    if ($subscriberInput == "111*100") {
      if (
        $response.serviceClassCurrent == "1003" ||
        $response.serviceClassCurrent == "1005"
      ) {
        setVariable("subscriberInput", "777*111");
      }
    }
    if ($subscriberInput == "135*051") {
      var today = new Date();
      var dateStr =
        today.getMonth() +
        1 +
        "/" +
        today.getDate() +
        "/" +
        today.getFullYear();
      const [month1, day1, year1] = dateStr.split("/");
      const dateyt = new Date(+year1, month1 - 1, +day1);
      const startStr = "11/26/2023";
      const [month2, day2, year2] = startStr.split("/");
      const startDateyt = new Date(+year2, month2 - 1, +day2);
      const endStr = "12/07/2023";
      const [month3, day3, year3] = endStr.split("/");
      const endDateyt = new Date(+year3, month3 - 1, +day3);
      if (dateyt >= startDateyt && dateyt <= endDateyt) {
        setVariable("subscriberInput", "135*051");
      } else {
        setVariable(
          "subMsg",
          "The UAE Union Day offer was for a limited time and is not available. For the best deals on voice and data, dial *135# or *055#."
        );
        setResponseCode("V3012");
        return "567fac9";
      }
    }
    //CR7013

    if ($subscriberInput == "111*100") {
      if (
        $response.serviceClassCurrent == "1001" ||
        $response.serviceClassCurrent == "1011"
      ) {
        setVariable("subscriberInput", "1111*100");
      }
    }

    //CR-6841 Alo New USSD + 6975
    if ($subscriberInput == "135*2562") {
      if (
        $response.serviceClassCurrent == "1002" &&
        _checkOfferIDExists($response.offerInformationList, "6789")
      ) {
        setVariable("subscriberInput", "111*100");
      }
      //WOW to Alo
      else if (
        $response.serviceClassCurrent == "1011" &&
        _checkOfferIDExists($response.offerInformationList, "6724")
      ) {
        setVariable("subscriberInput", "135*7014");
      }
      //Flexi to Alo
      else if (
        $response.serviceClassCurrent == "1010" &&
        _checkOfferIDExists($response.offerInformationList, "6724")
      ) {
        setVariable("subscriberInput", "135*6012");
      } else {
        setVariable("respCode", "E2009");
        setVariable(
          "message",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
      }
    }
    //CR-6975
    if ($subscriberInput == "135*2563") {
      // WOW to PAYG
      if (
        $response.serviceClassCurrent == "1011" &&
        _checkOfferIDExists($response.offerInformationList, "6724")
      ) {
        setVariable("subscriberInput", "135*7013");
      }

      //Flexi to PAYG
      else if (
        $response.serviceClassCurrent == "1010" &&
        _checkOfferIDExists($response.offerInformationList, "6724")
      ) {
        setVariable("subscriberInput", "135*6011");
      } else {
        setVariable("respCode", "E2009");
        setVariable(
          "message",
          "You are not eligible for this offer. Go to store to move to Flexi Rate Plan"
        );
      }
    }
    //<<<<Sec: CR-7369 & CR-7369 D2 Prepaid USSD Update -- Start>>>>

    if ($subscriberInput == "555" || $subscriberInput == "777") {
      setVariable("subscriberInput", "800");
    }
    if ($subscriberInput == "7213") {
      setVariable("subscriberInput", "555");
    }
    //<<<<Sec: CR-7369 & CR-7369 D2 Prepaid USSD Update -- End>>>>

    //Ent Flexi
    if (_contains($response.serviceClassCurrent, "1051")) {
      let offer100 = _parseOfferInformationList(
        $response.offerInformationList,
        "100"
      );
      let attribute100 = offer100.attributeInformationList;
      let EntFlexiCommitBndle = [];
      if (attribute100) {
        attribute100.forEach((attribute) => {
          if (attribute.attributeName == "EntFlexiCommitBundle") {
            EntFlexiCommitBndle.push(attribute.attributeValueString);
          }
        });
      }
      if (
        $subscriberInput == "135*1" ||
        $subscriberInput == "135*4" ||
        $subscriberInput == "135*5" ||
        $subscriberInput == "135*41" ||
        $subscriberInput == "135*42" ||
        $subscriberInput == "135*43" ||
        $subscriberInput == "135*44" ||
        $subscriberInput == "135*71" ||
        $subscriberInput == "135*85" ||
        $subscriberInput == "135*225" ||
        $subscriberInput == "135*300" ||
        $subscriberInput == "135*422" ||
        $subscriberInput == "135*433"
      ) {
        if (
          _checkOfferIDExists($response.offerInformationList, 5160) == 1 ||
          EntFlexiCommitBndle[0] == "EntFlexiV1D1" ||
          EntFlexiCommitBndle[0] == "EntFlexiV1D2" ||
          EntFlexiCommitBndle[0] == "EntFlexiV1D3" ||
          EntFlexiCommitBndle[0] == "EntFlexiV2D1" ||
          EntFlexiCommitBndle[0] == "EntFlexiV2D2" ||
          EntFlexiCommitBndle[0] == "EntFlexiV2D3" ||
          EntFlexiCommitBndle[0] == "EntFlexiV3D1" ||
          EntFlexiCommitBndle[0] == "EntFlexiV3D2" ||
          EntFlexiCommitBndle[0] == "EntFlexiV3D3"
        ) {
          setVariable(
            "subMsg",
            "This service is not available on your current Business Flexi Prepaid Plan."
          );
          setResponseCode("V3012");
          return "567fac9";
        } else {
          return "4a19e38";
        }
      } else if ($subscriberInput == "135*50") {
        if (_checkOfferIDExists($response.offerInformationList, 5160) == 1) {
          return "4a19e38";
        } else {
          setResponseCode("V3008");
          return "567fac9";
        }
      } else if (
        ($subscriberInput == "135*35" ||
          $subscriberInput == "135*7" ||
          $subscriberInput == "135*11" ||
          $subscriberInput == "135*30") &&
        _checkOfferIDExists($response.offerInformationList, 5160)
      ) {
        if (
          _checkOfferIDExists($response.offerInformationList, 5161) ||
          _checkOfferIDExists($response.offerInformationList, 5162) ||
          _checkOfferIDExists($response.offerInformationList, 5163)
        ) {
          return "4a19e38";
        } else {
          setVariable(
            "subMsg",
            "To buy selected offer first activate your Business Flexi Prepaid Plan."
          );
          setResponseCode("V3012");
          return "567fac9";
        }
      }
    }

    return "4a19e38"; // return end moduleId
  } catch (e) {
    console.log(e);
  }
}
