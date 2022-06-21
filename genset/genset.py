def main():
    simMinLoops = 4000
    simMaxLoops = 75000
    isUniversal = 0

    popIncrements = 3
    startingPopsMinVal = 2000
    startingPopsMaxVal = 50000
    startingPopsStep = (startingPopsMaxVal - startingPopsMinVal) / popIncrements
    startingPopsRange = [ int((i * startingPopsStep) + startingPopsMinVal) for i in range(1 + popIncrements)]

    avgAgeIncrements = 5
    avgAgeMinVal = 100
    avgAgeMaxVal = 2000
    avgAgeStep = (avgAgeMaxVal- avgAgeMinVal) / avgAgeIncrements
    avgAgeRange = [ int((i * avgAgeStep) + avgAgeMinVal) for i in range(1 + avgAgeIncrements)]

    MFRIncrements = 1
    startingMFRatioMinVal = 0.2
    startingMFRatioMaxVal = 0.8
    startingMFRatioStep = (startingMFRatioMaxVal - startingMFRatioMinVal) / MFRIncrements
    startingMFRatioRange = [ round((i * startingMFRatioStep) + startingMFRatioMinVal, 3) for i in range(1 + MFRIncrements)]

    FPRIncrements = 2
    startingFPRatioMinVal = 0.1
    startingFPRatioMaxVal = 0.9
    startingFPRatioStep = (startingFPRatioMaxVal - startingFPRatioMinVal) / FPRIncrements
    startingFPRatioRange = [ round((i * startingFPRatioStep) + startingFPRatioMinVal, 3) for i in range(1 + FPRIncrements)]

    CSRIncrements = 2
    startingCSRatioMinVal = 0.1
    startingCSRatioMaxVal = 0.9
    startingCSRatioStep = (startingCSRatioMaxVal - startingCSRatioMinVal) / CSRIncrements
    startingCSRatioRange = [ round((i * startingCSRatioStep) + startingCSRatioMinVal, 3) for i in range(1 + CSRIncrements)]

    aCostIncrements = 0
    startingACostMinVal = 0
    startingACostMaxVal = 100
    #  startingACostStep = (startingACostMaxVal - startingACostMinVal) / aCostIncrements
    startingACostRange = [ int((i * 0) + startingACostMinVal) for i in range(1 + aCostIncrements) ]

    bBenefitIncrements = 4
    startingBBEnefitMinVal = 0
    startingBBEnefitMaxVal = 100
    startingBBenefitStep = (startingBBEnefitMaxVal - startingBBEnefitMinVal) / bBenefitIncrements
    startingBBenefitRange = [ int((i * startingBBenefitStep) + startingBBEnefitMinVal) for i in range(1 + bBenefitIncrements) ]

    cCostIncrements = 4
    startingCCostMinVal = 0
    startingCCostMaxVal = 100
    startingCCostStep = (startingCCostMaxVal - startingCCostMinVal) / cCostIncrements
    startingCCostRange = [ int((i * startingCCostStep) + startingCCostMinVal) for i in range(1 + cCostIncrements) ]

    simCount = 0

    with open("settings.xml", "w") as cfgfile:
        cfgfile.write("<settings>\n")

        for i in range(1 + popIncrements):
            for p in range(1 + avgAgeIncrements):
                for j in range(1 + MFRIncrements):
                    for k in range(1 + FPRIncrements):
                        for l in range(1 + CSRIncrements):
                            for m in range(1 + aCostIncrements):
                                for n in range(1 + bBenefitIncrements):
                                    for o in range(1 + cCostIncrements):
                                        cfgfile.write("<simulation>")
                                        cfgfile.write(f"<mutAllowed>true</mutAllowed>") # Allow genetic mutation
                                        cfgfile.write(f"<minSimLoops>{simMinLoops}</minSimLoops>")
                                        cfgfile.write(f"<maxSimLoops>{simMaxLoops}</maxSimLoops>")
                                        cfgfile.write(f"<averageAge>{avgAgeRange[p]}</averageAge>")
                                        cfgfile.write(f"<population>{startingPopsRange[i]}</population>")
                                        cfgfile.write(f"<mfRatio>{startingMFRatioRange[j]}</mfRatio>")
                                        cfgfile.write(f"<fpRatio>{startingFPRatioRange[k]}</fpRatio>")
                                        cfgfile.write(f"<csRatio>{startingCSRatioRange[l]}</csRatio>")
                                        cfgfile.write(f"<a>{startingACostRange[m]}</a>")
                                        cfgfile.write(f"<b>{startingBBenefitRange[n]}</b>")
                                        cfgfile.write(f"<c>{startingCCostRange[o]}</c>")
                                        cfgfile.write(f"<isUniversal>{isUniversal}</isUniversal>")
                                        cfgfile.write("</simulation>\n")
                                        simCount += 1

        cfgfile.write("</settings>\n")
        cfgfile.write(f"<!-- TOTAL SIMS: {simCount} -->")

    print("TOTAL SIMULATIONS:", simCount)

if __name__ == "__main__":
    main()
