import pandas as pd
import numpy as np
data= pd.read_csv("andGATE.csv")
inputs=np.array(data[['x0','x1','x2']])
inputs.tolist()
output=data['y']
output.tolist()

theeta=1
learning_rate=0.1
iterations=100
w=np.zeros(len(inputs[0]))
predicted=np.ones(len(output))
errors=np.ones(len(output))

n=0

def Learning(inputs,labels,threshold,iterations,alpha):
    w=np.zeros(len(inputs[0]))
    predicted=np.ones(len(labels))
    errors=np.ones(len(labels))
    n=0
    while n < iterations:
        for i in range(0,len(inputs)):
            pred_output=np.dot(inputs[i],w)
            if pred_output > threshold:
                yhat=1.
            else:
                yhat=0.
            for j in range (0,len(w)):
                error=(labels[i]-yhat)**2
                w[j]=w[j]+alpha*(labels[i]-yhat)*inputs[i][j]
                #print(w[j])
            file=open("test.txt","w")
            for item in w:
                file.write("%s\n" % item)
            file.close()
            
            print("Inputs",inputs[i]) 
            print("Weights",w)
            print("error",error)
            n=n+1
    return w

if __name__=='__main__':
    updated_weights=Learning(inputs,output,theeta,iterations,learning_rate)
    print("Final updated weights",updated_weights)
    for i in range(0,len(inputs)):
        yp=np.dot(inputs[i],updated_weights)
        if yp > theeta:
            show=1.
        else:
            show=0.
        print("Predicted output",show)
