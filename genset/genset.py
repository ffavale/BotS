
def main():

    popIncrements = 2
    startingPopsMinVal = 500
    startingPopsMaxVal = 10000
    startingPopsStep = (startingPopsMaxVal - startingPopsMinVal) / popIncrements
    startingPopsRange = [ int((i * startingPopsStep) + startingPopsMinVal) for i in range(popIncrements)]

    MFRIncrements = 1
    startingMFRatioMinVal = 0.5
    startingMFRatioMaxVal = 0.5
    startingMFRatioStep = (startingMFRatioMaxVal - startingMFRatioMinVal) / MFRIncrements
    startingMFRatioRange = [ round((i * startingMFRatioStep) + startingMFRatioMinVal, 3) for i in range(MFRIncrements)]

    FPRIncrements = 2
    startingFPRatioMinVal = 0.1
    startingFPRatioMaxVal = 0.9
    startingFPRatioStep = (startingFPRatioMaxVal - startingFPRatioMinVal) / FPRIncrements
    startingFPRatioRange = [ round((i * startingFPRatioStep) + startingFPRatioMinVal, 3) for i in range(FPRIncrements)]

    CSRIncrements = 2
    startingCSRatioMinVal = 0.1
    startingCSRatioMaxVal = 0.9
    startingCSRatioStep = (startingCSRatioMaxVal - startingCSRatioMinVal) / CSRIncrements
    startingCSRatioRange = [ round((i * startingCSRatioStep) + startingCSRatioMinVal, 3) for i in range(CSRIncrements)]

    aCostIncrements = 2
    startingACostMinVal = 0
    startingACostMaxVal = 100
    startingACostStep = (startingACostMaxVal - startingACostMinVal) / aCostIncrements
    startingACostRange = [ int((i * startingACostStep) + startingACostMinVal) for i in range(aCostIncrements) ]


    bBenefitIncrements = 2
    startingBBEnefitMinVal = 0
    startingBBEnefitMaxVal = 100
    startingBBenefitStep = (startingBBEnefitMaxVal - startingBBEnefitMinVal) / bBenefitIncrements
    startingBBenefitRange = [ int((i * startingBBenefitStep) + startingBBEnefitMinVal) for i in range(bBenefitIncrements) ]

    cCostIncrements = 2
    startingCCostMinVal = 0
    startingCCostMaxVal = 100
    startingCCostStep = (startingCCostMaxVal - startingCCostMinVal) / cCostIncrements
    startingCCostRange = [ int((i * startingCCostStep) + startingCCostMinVal) for i in range(cCostIncrements) ]

    simCount = 0

    with open("settings.xml", "w") as cfgfile:
        cfgfile.write("<settings>\n")

        for i in range(popIncrements):
            for j in range(MFRIncrements):
                for k in range(FPRIncrements):
                    for l in range(CSRIncrements):
                        for m in range(aCostIncrements):
                            for n in range(bBenefitIncrements):
                                for o in range(cCostIncrements):
                                    cfgfile.write("\t<simulation>\n")
                                    cfgfile.write(f"\t\t<population>{startingPopsRange[i]}</population>\n")
                                    cfgfile.write(f"\t\t<mfRatio>{startingMFRatioRange[j]}</mfRatio>\n")
                                    cfgfile.write(f"\t\t<fpRatio>{startingFPRatioRange[k]}</fpRatio>\n")
                                    cfgfile.write(f"\t\t<csRatio>{startingCSRatioRange[l]}</csRatio>\n")
                                    cfgfile.write(f"\t\t<a>{startingACostRange[m]}</a>\n")
                                    cfgfile.write(f"\t\t<b>{startingBBenefitRange[n]}</b>\n")
                                    cfgfile.write(f"\t\t<c>{startingCCostRange[o]}</c>\n")
                                    cfgfile.write("\t</simulation>\n")
                                    simCount += 1

        cfgfile.write("</settings>\n")

    print("TOTAL SIMULATIONS:", simCount)

if __name__ == "__main__":
    main()
