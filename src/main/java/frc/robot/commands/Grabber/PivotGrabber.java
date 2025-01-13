// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.subsystems.GrabberSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class PivotGrabber extends Command {
  GrabberSubsystem grabberSub;
  GrabberStates state;
  double grabberSetPoint;
  double currentSetPoint;

  double speed;

  /** Creates a new MoveToGrabberState. */
  public PivotGrabber(GrabberSubsystem grabberSub, GrabberStates state, double speed) {
    this.grabberSub=grabberSub;
    this.speed=speed;
    this.state=state;
    addRequirements(grabberSub);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state=grabberSub.getState();
    grabberSetPoint=state.getGrabberSetPoint();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
currentSetPoint=grabberSub.getPivotEncoder();
if (grabberSetPoint>currentSetPoint){
  grabberSub.movePivotMotor(speed);
}
else if (grabberSetPoint<currentSetPoint){
  grabberSub.movePivotMotor(-speed);
}
else {
  grabberSub.movePivotMotor(0);
}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    grabberSub.movePivotMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(currentSetPoint-grabberSetPoint)<0.5) {
      return true;
    }
    else {
      return false;
    }
  }
}
