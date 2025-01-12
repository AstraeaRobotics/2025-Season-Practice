// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Grabber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants.GrabberConstants.GrabberStates;
import frc.robot.subsystems.GrabberSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SetGrabberState extends InstantCommand {

  private final GrabberSubsystem m_grabber;
  private final GrabberStates m_desiredState;

  public SetGrabberState(GrabberSubsystem grabber, GrabberStates state) {
    m_desiredState = state;
    m_grabber = grabber;

    addRequirements(grabber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_grabber.setState(m_desiredState);
  }
}
