package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.State;

@TeleOp

public class NewNewMecanum extends OpMode
{
    private enum State
    {
        STATE_ARKANSAS,
        STATE_ALABAMA,
        STATE_CALIFORNIA,
        STATE_NEWMEXICO,
        STATE_BROKEN,
    }

    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor backleft;
    public DcMotor backright;
    public DcMotor flippy;
    private DcMotor pully;
    private DcMotor suck;

    public ElapsedTime Runtime = new ElapsedTime();

    private ElapsedTime Statetime = new ElapsedTime();

    private State CurrentState;


    public NewNewMecanum()
    {
    }

    @Override
    public void init()
    {
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");
        flippy = hardwareMap.get(DcMotor.class, "flippy");
        suck   = hardwareMap.get(DcMotor.class, "suck");
        pully = hardwareMap.get(DcMotor.class, "pully");
        
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        flippy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        flippy.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pully.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pully.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void start()
    {
        Runtime.reset();
        newState(State.STATE_ARKANSAS);
        
    }

    @Override
    public void loop()
    {
        telemetry.addData("0", String.format("%4.1f", Statetime.time()) + CurrentState.toString());

        int pposition = pully.getCurrentPosition();

        double drive  = -gamepad1.left_stick_y;
        double strafe = gamepad1.left_stick_x;
        double twist  = gamepad1.right_stick_x;

        double[] speeds = {
            (drive + strafe + twist),
            (drive - strafe - twist),
            (drive - strafe + twist),
            (drive + strafe - twist)
        };
        
        double max = Math.abs(speeds[0]);
        for(int i = 0; i < speeds.length; i++) {
            if ( max < Math.abs(speeds[i]) ) max = Math.abs(speeds[i]);
        }

        if (max > 1) {
            for (int i = 0; i < speeds.length; i++) speeds[i] /= max;
        }

        switch (CurrentState)
        {
            case STATE_ARKANSAS:
                flippy.setPower(0);
                if (gamepad2.b)
                {
                    newState(State.STATE_ALABAMA);
                }
                else if(gamepad2.x)
                {
                    newState(State.STATE_CALIFORNIA);
                }
                else if(gamepad2.y)
                {
                    newState(State.STATE_NEWMEXICO);
                }
                else if(gamepad1.dpad_up)
                {
                    newState(State.STATE_BROKEN);
                }
                else
                {
                    if (gamepad1.right_bumper){
                        frontleft.setPower(speeds[0]*.3);
                        frontright.setPower(speeds[1]*.3);
                        backleft.setPower(speeds[2]*.3);
                        backright.setPower(speeds[3]*.3);
                    }
                    else
                    {
                        frontleft.setPower(speeds[0]);
                        frontright.setPower(speeds[1]);
                        backleft.setPower(speeds[2]);
                        backright.setPower(speeds[3]);
                    }
                    
                    
                    
                        if(gamepad2.left_bumper){
                            if(gamepad2.right_trigger>0){
                                suck.setPower(gamepad2.right_trigger*0.35);
                            }else{
                                suck.setPower(0);
                            }
                        }else{ 
                            if(gamepad2.right_trigger>0){
                                suck.setPower(gamepad2.right_trigger);
                            }else if(gamepad2.left_trigger>0){
                                suck.setPower(-gamepad2.left_trigger);
                            }else{
                                suck.setPower(0);
                            }
                        }
                    
                        
                        
                        
                    if(pposition<700){
                        pully.setPower(-gamepad2.left_stick_y/3);
                    }else if(pposition>700){
                        pully.setPower(-gamepad2.left_stick_y/10);
                    }
                    
                    
                    
                }
                
                break;

            case STATE_ALABAMA:
                if(gamepad2.x)
                {
                    newState(State.STATE_CALIFORNIA);
                }
                else if(gamepad2.a)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else if(Statetime.time()>1.25)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else
                {
                    if(Statetime.time()<1)
                    {
                        if(Statetime.time()<.5)
                        {
                            flippy.setTargetPosition(2800);
                            flippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            flippy.setPower(Statetime.time()*1.5);
                        }
                        else
                        {
                            flippy.setPower(.75);
                        }
                
                        if (gamepad1.right_bumper){
                            frontleft.setPower(speeds[0]*.3);
                           frontright.setPower(speeds[1]*.3);
                            backleft.setPower(speeds[2]*.3);
                            backright.setPower(speeds[3]*.3);
                        }
                        else
                        {
                            frontleft.setPower(speeds[0]);
                            frontright.setPower(speeds[1]);
                            backleft.setPower(speeds[2]);
                            backright.setPower(speeds[3]);
                        }
                    }
                    else
                    {
                        flippy.setPower(0);
                    }
                }

                break;

            case STATE_CALIFORNIA:
                if(gamepad2.b)
                {
                    newState(State.STATE_ALABAMA);
                }
                else if(gamepad2.a)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else if(Statetime.time()>1.75)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else
                {
                    if(Statetime.time()<1.25)
                    {
                        if(Statetime.time()<.5)
                        {
                            flippy.setTargetPosition(400);
                            flippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            flippy.setPower(Statetime.time()*1.5);
                        }
                        else
                        {
                            flippy.setPower(.75);
                        }
                    }
                    else
                    {
                        flippy.setPower(0);
                    }
                    
                    if (gamepad1.right_bumper){
                        frontleft.setPower(speeds[0]*.3);
                        frontright.setPower(speeds[1]*.3);
                        backleft.setPower(speeds[2]*.3);
                        backright.setPower(speeds[3]*.3);
                    }
                    else
                    {
                        frontleft.setPower(speeds[0]);
                        frontright.setPower(speeds[1]);
                        backleft.setPower(speeds[2]);
                        backright.setPower(speeds[3]);
                    }
                }

                break;
                
                

            case STATE_NEWMEXICO:
                if(gamepad2.b)
                {
                    newState(State.STATE_ALABAMA);
                }
                else if(gamepad2.a)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else if(gamepad2.x)
                {
                    newState(State.STATE_CALIFORNIA);
                }
                else
                {
                    flippy.setTargetPosition(1500);
                    flippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    flippy.setPower(Statetime.time()*1.5);
                    
                    if (gamepad1.right_bumper){
                        frontleft.setPower(speeds[0]*.3);
                        frontright.setPower(speeds[1]*.3);
                        backleft.setPower(speeds[2]*.3);
                        backright.setPower(speeds[3]*.3);
                    }
                    else
                    {
                        frontleft.setPower(speeds[0]);
                        frontright.setPower(speeds[1]);
                        backleft.setPower(speeds[2]);
                        backright.setPower(speeds[3]);
                    }
                }

                break;
                
                case STATE_BROKEN:
                if(gamepad2.x)
                {
                    newState(State.STATE_CALIFORNIA);
                }
                else if(gamepad2.a)
                {
                    newState(State.STATE_ARKANSAS);
                }
                else if(Statetime.time()>1.25)
                {
                    flippy.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    newState(State.STATE_ARKANSAS);
                }
                else
                {
                    if(Statetime.time()<1)
                    {
                        if(Statetime.time()<.5)
                        {
                            flippy.setTargetPosition(-2800);
                            flippy.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                            flippy.setPower(Statetime.time()*1.5);
                        }
                        else
                        {
                            flippy.setPower(.75);
                        }
                
                        if (gamepad1.right_bumper){
                            frontleft.setPower(speeds[0]*.3);
                           frontright.setPower(speeds[1]*.3);
                            backleft.setPower(speeds[2]*.3);
                            backright.setPower(speeds[3]*.3);
                        }
                        else
                        {
                            frontleft.setPower(speeds[0]);
                            frontright.setPower(speeds[1]);
                            backleft.setPower(speeds[2]);
                            backright.setPower(speeds[3]);
                        }
                    }
                    else
                    {
                        flippy.setPower(0);
                    }
                }
        }
    }


    private void newState(State newState)
    {
        Statetime.reset();
        CurrentState = newState;
    }

    
}